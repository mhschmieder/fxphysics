/**
 * MIT License
 *
 * Copyright (c) 2020, 2023 Mark Schmieder
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the FxPhysicsGui Library
 *
 * You should have received a copy of the MIT License along with the
 * FxPhysicsGui Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxphysicsgui
 */
package com.mhschmieder.fxphysicsgui.control;

import com.mhschmieder.commonstoolkit.util.ClientProperties;
import com.mhschmieder.fxguitoolkit.control.TextSelector;
import com.mhschmieder.physicstoolkit.DistanceUnit;

/**
 * The Distance Unit selector supports all Distance Units that are currently
 * implemented in our core Math Library.
 * <p>
 * TODO: Redo as an enum-based XComboBox that provides a List Cell Factory,
 *  but possibly use the "Choose One" text as a placeholder for null selection?
 */
public class DistanceUnitSelector extends TextSelector {

    public static final String    CHOOSE_ONE               = "Choose One";                               //$NON-NLS-1$

    private static final String[] DISTANCE_UNITS_KNOWN     =
                                                       new String[] {
                                                                      DistanceUnit.METERS
                                                                              .label(),
                                                                      DistanceUnit.CENTIMETERS
                                                                              .label(),
                                                                      DistanceUnit.MILLIMETERS
                                                                              .label(),
                                                                      DistanceUnit.YARDS
                                                                              .label(),
                                                                      DistanceUnit.FEET
                                                                              .label(),
                                                                      DistanceUnit.INCHES
                                                                              .label() };
    private static final String[] DISTANCE_UNITS_AMBIGUOUS =
                                                           new String[] {
                                                                          CHOOSE_ONE,
                                                                          DistanceUnit.METERS
                                                                                  .label(),
                                                                          DistanceUnit.CENTIMETERS
                                                                                  .label(),
                                                                          DistanceUnit.MILLIMETERS
                                                                                  .label(),
                                                                          DistanceUnit.YARDS
                                                                                  .label(),
                                                                          DistanceUnit.FEET
                                                                                  .label(),
                                                                          DistanceUnit.INCHES
                                                                                  .label() };

    public DistanceUnitSelector( final ClientProperties clientProperties,
                                 final boolean applyToolkitCss,
                                 final boolean includeChooseOne,
                                 final DistanceUnit distanceUnit ) {
        // Always call the superclass constructor first!
        super( clientProperties,
               "Supported Distance Units", //$NON-NLS-1$
               applyToolkitCss,
               false,
               false,
               10,
               includeChooseOne ? CHOOSE_ONE : distanceUnit.label(),
               includeChooseOne ? DISTANCE_UNITS_AMBIGUOUS : DISTANCE_UNITS_KNOWN );
    }

    public final DistanceUnit getDistanceUnit() {
        final String distanceUnitString = getTextValue();
        final DistanceUnit distanceUnit = CHOOSE_ONE.equals( distanceUnitString )
            ? DistanceUnit.UNITLESS
            : DistanceUnit.defaultValue().valueOfLabel( distanceUnitString );
        return distanceUnit;
    }

    public final void setDistanceUnit( final DistanceUnit distanceUnit ) {
        final String distanceUnitString = DistanceUnit.UNITLESS.equals( distanceUnit )
            ? CHOOSE_ONE
            : distanceUnit.label();
        setTextValue( distanceUnitString );
    }
}
