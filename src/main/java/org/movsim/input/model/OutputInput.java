/**
 * Copyright (C) 2010, 2011 by Arne Kesting, Martin Treiber,
 *                             Ralph Germ, Martin Budden
 *                             <info@movsim.org>
 * ----------------------------------------------------------------------
 * 
 *  This file is part of 
 *  
 *  MovSim - the multi-model open-source vehicular-traffic simulator 
 *
 *  MovSim is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  MovSim is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MovSim.  If not, see <http://www.gnu.org/licenses/> or
 *  <http://www.movsim.org>.
 *  
 * ----------------------------------------------------------------------
 */
package org.movsim.input.model;

import org.movsim.input.model.output.DetectorInput;
import org.movsim.input.model.output.FloatingCarInput;
import org.movsim.input.model.output.MacroInput;
import org.movsim.input.model.output.TrafficLightRecorderInput;
import org.movsim.input.model.output.TrajectoriesInput;

// TODO: Auto-generated Javadoc
/**
 * The Interface OutputInput.
 */
public interface OutputInput {

    /**
     * Gets the floating car input.
     * 
     * @return the floating car input
     */
    FloatingCarInput getFloatingCarInput();

    /**
     * Gets the macro input.
     * 
     * @return the macro input
     */
    MacroInput getMacroInput();

    /**
     * Gets the detector input.
     * 
     * @return the detector input
     */
    DetectorInput getDetectorInput();

    /**
     * Gets the traffic light recorder input.
     * 
     * @return the traffic light recorder input
     */
    TrafficLightRecorderInput getTrafficLightRecorderInput();

    
    /**
     * Gets the trajectories input.
     *
     * @return the trajectories input
     */
    TrajectoriesInput getTrajectoriesInput();
    
    
}