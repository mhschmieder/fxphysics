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
 * This file is part of the FxPhysics Library
 *
 * You should have received a copy of the MIT License along with the
 * FxPhysics Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxphysics
 */
package com.mhschmieder.fxphysics.control;

import com.mhschmieder.commonstoolkit.util.ClientProperties;
import com.mhschmieder.fxguitoolkit.control.DoubleEditor;

public final class HumidityEditor extends DoubleEditor {

    // Declare value increment/decrement amount for up and down arrow keys.
    // NOTE: We increment by 10% as this is a typical default.
    // TODO: Modify this value if units are other than Relative Humidity, and
    // also add supporting methods for unit conversion and display?
    public static final double VALUE_INCREMENT_PERCENT = 10.0d;

    public HumidityEditor( final ClientProperties pClientProperties,
                           final String initialText,
                           final String tooltipText,
                           final double humidityMinimum,
                           final double humidityMaximum,
                           final double humidityInitial ) {
        // Always call the superclass constructor first!
        // NOTE: We use up to two decimal places of precision for displaying
        // humidity, and four decimal places for parsing humidity.
        super( pClientProperties,
               initialText,
               tooltipText,
               true,
               0,
               2,
               0,
               4,
               humidityMinimum,
               humidityMaximum,
               humidityInitial,
               VALUE_INCREMENT_PERCENT );
    }
}
