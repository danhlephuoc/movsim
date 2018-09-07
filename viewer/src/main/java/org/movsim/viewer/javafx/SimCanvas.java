package org.movsim.viewer.javafx;


import javafx.scene.canvas.GraphicsContext;
import org.jfree.fx.FXGraphics2D;
import org.movsim.autogen.Movsim;
import org.movsim.input.ProjectMetaData;
import org.movsim.simulator.SimulationRunnable;
import org.movsim.simulator.Simulator;
import org.movsim.simulator.roadnetwork.RoadNetwork;
import org.movsim.xml.InputLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.Properties;

class SimCanvas extends javafx.scene.canvas.Canvas implements SimulationRunnable.UpdateDrawingCallback, SimulationRunnable.HandleExceptionCallback {

    private static final Logger LOG = LoggerFactory.getLogger(SimCanvas.class);

    Simulator simulator;
    private SimulationRunnable simulationRunnable = null;
    private RoadNetwork roadNetwork;

    public Settings settings = new Settings();
    private Properties viewProperties;

    private DrawMovables drawMovables;
    private DrawBackground drawBackground;

    // Facade to draw with awt on a javafx canvas TODO convert everything javafx to step by step -> remove
    private FXGraphics2D fxGraphics2D;
    final GraphicsContext gc;

    private SimCanvas.StatusControlCallbacks statusControlCallbacks;

    private long totalAnimationTime;

    public SimCanvas(int width, int height, Properties viewProperties) {
        super(width, height);
        this.viewProperties = viewProperties;

        // Facade for using awt TODO replace all awt calls with javafx
        gc = getGraphicsContext2D();
        fxGraphics2D = new FXGraphics2D(this.getGraphicsContext2D());

        init();
    }


    void init() {
        ProjectMetaData projectMetaData = ProjectMetaData.getInstance();
        Movsim movsimInput = InputLoader.unmarshallMovsim(projectMetaData.getInputFile());

        simulator = new Simulator(movsimInput);

        simulationRunnable = simulator.getSimulationRunnable();
        simulationRunnable.setUpdateDrawingCallback(this);
        simulationRunnable.setHandleExceptionCallback(this);

        roadNetwork = simulator.getRoadNetwork();

        drawMovables = new DrawMovables(settings, roadNetwork, simulationRunnable);
        drawBackground = new DrawBackground(settings, roadNetwork, simulationRunnable, simulator);

        if (projectMetaData.hasProjectName()) {
            try {
                simulator.initialize();
            } catch (JAXBException | SAXException e) {
                throw new RuntimeException("Jaxb exception:" + e.toString());
            }
            initGraphicSettings();
            start();
        } else {
            System.err.println("Please provide scenario via -f option");
            System.exit(-1);
        }
    }

    void resetSimulation() {
        simulator.reset();
    }

    /**
     * Set the thread sleep time. This controls the animation speed.
     */
    final void setSleepTime(int sleepTime_ms) {
        simulationRunnable.setSleepTime(sleepTime_ms);
    }

    void execScale() {
        gc.scale(settings.getScale(), settings.getScale());
    }

    void execTranslateFx() {
        gc.translate(settings.getxOffset(), settings.getyOffset());
    }

    private void initGraphicSettings() {
        settings.initGraphicConfigFieldsFromProperties(viewProperties);
        setSleepTime(Integer.parseInt(viewProperties.getProperty("initial_sleep_time")));

        for (String vehicleTypeLabel : simulator.getVehiclePrototypeLabels()) {
            LOG.debug("set color for vehicle label={}", vehicleTypeLabel);
            settings.getLabelColors().put(vehicleTypeLabel, javafx.scene.paint.Color.color(Math.random(), Math.random(), Math.random()));
        }

        execScale();
        execTranslateFx();
    }

    public void start() {
        totalAnimationTime = 0;
        simulationRunnable.start();
        stateChanged();
    }

    // ============================================================================================
    // SimulationRunnable callbacks
    // ============================================================================================

    /**
     * <p>
     * Implements SimulationRunnable.UpdateDrawingCallback.updateDrawing().
     * </p>
     */
    public void updateDrawing(double simulationTime) {
        drawBackground.update(fxGraphics2D, this.getWidth(), this.getHeight(), gc);
        drawMovables.update(gc);
//        simulationRunnable.pause();
    }

    /**
     * <p>
     * Implements SimulationRunnable.HandleExceptionCallback.handleException().
     * </p>
     * <p>
     * Called back from the TrafficRunnable thread, in the synchronization block, if an exception occurs.
     * </p>
     */
    @Override
    public void handleException(Exception e) {
    }


    void setStatusControlCallbacks(SimCanvas.StatusControlCallbacks statusCallbacks) {
        this.statusControlCallbacks = statusCallbacks;
    }

    void stateChanged() {
        if (statusControlCallbacks != null) {
            statusControlCallbacks.stateChanged();
        }
    }

    void triggerRepaint() {
        drawBackground.update(fxGraphics2D, this.getWidth(), this.getHeight(), gc);
        drawMovables.update(gc);
    }

    /**
     * Callbacks from this TrafficCanvas to the application UI.
     */
    public interface StatusControlCallbacks {
        /**
         * Callback to get the UI to display a status message.
         *
         * @param message the status message
         */
        public void showStatusMessage(String message);

        public void stateChanged();
    }

}