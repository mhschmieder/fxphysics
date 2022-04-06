/**
 * MIT License
 *
 * Copyright (c) 2020, 2022 Mark Schmieder
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
 * This file is part of the FxPhysicsToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * FxPhysicsToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxphysicstoolkit
 */
package com.mhschmieder.fxphysicstoolkit.acoustics.control;

import com.mhschmieder.commonstoolkit.util.ClientProperties;
import com.mhschmieder.fxguitoolkit.control.DoubleEditor;
import com.mhschmieder.mathtoolkit.MathExt;

public final class FrequencyEditor extends DoubleEditor {

    // Declare value increment/decrement amount for up and down arrow keys.
    public static final double VALUE_INCREMENT_HZ = 0.1d;

    public FrequencyEditor( final ClientProperties pClientProperties,
                            final String initialText,
                            final String tooltipText,
                            final double frequencyMinimumHz,
                            final double frequencyMaximumHz,
                            final double frequencyInitialHz ) {
        // Always call the superclass constructor first!
        // NOTE: We use up to one decimal place of precision for displaying
        // frequency, and four decimal places for parsing frequency.
        super( pClientProperties,
               initialText,
               tooltipText,
               0,
               1,
               0,
               4,
               frequencyMinimumHz,
               frequencyMaximumHz,
               frequencyInitialHz,
               VALUE_INCREMENT_HZ );
    }

    @Override
    public double adjustPrecision( final double doubleValue ) {
        final double precisionAdjustedValue = ( doubleValue >= 100d )
            ? Math.round( doubleValue )
            : MathExt.nearestDecimal( doubleValue, 1 );
        return precisionAdjustedValue;
    }

}
