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
package org.movsim.input.model.vehicle.behavior;

// TODO: Auto-generated Javadoc
/**
 * The Interface MemoryInputData.
 */
public interface MemoryInputData {

    /**
     * Gets the tau.
     * 
     * @return the tau
     */
    public abstract double getTau();

    /**
     * Gets the resignation max alpha t.
     * 
     * @return the resignation max alpha t
     */
    public abstract double getResignationMaxAlphaT();

    /**
     * Gets the resignation min alpha v0.
     * 
     * @return the resignation min alpha v0
     */
    public abstract double getResignationMinAlphaV0();

    /**
     * Gets the resignation min alpha a.
     * 
     * @return the resignation min alpha a
     */
    public abstract double getResignationMinAlphaA();

}