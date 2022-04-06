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
import com.mhschmieder.fxguitoolkit.control.TextSelector;
import com.mhschmieder.physicstoolkit.acoustics.Smoothing;

/**
 * This is a selector for different Smoothing resolutions.
 */
public final class SmoothingSelector extends TextSelector {

    // Default Smoothing, for best "out of box" experience.
    public static final String    SMOOTHING_DEFAULT =
                                                    Smoothing.defaultValue().toPresentationString();

    private static final String[] SMOOTHINGS        =
                                             new String[] {
                                                            Smoothing.NARROW_BAND
                                                                    .toPresentationString(),
                                                            Smoothing.SIXTH_OCTAVE_BAND
                                                                    .toPresentationString(),
                                                            Smoothing.THIRD_OCTAVE_BAND
                                                                    .toPresentationString() };

    public SmoothingSelector( final ClientProperties pClientProperties,
                              final boolean pToolbarContext ) {
        // Always call the superclass constructor first!
        super( pClientProperties,
               "Frequency Response Smoothing", //$NON-NLS-1$
               pToolbarContext,
               false,
               false,
               SMOOTHINGS,
               SMOOTHING_DEFAULT );
    }

    public Smoothing getSmoothing() {
        return Smoothing.fromPresentationString( getTextValue() );
    }

    public int getSmoothingOctaveDivider() {
        final Smoothing smoothing = getSmoothing();
        final int octaveDivider = smoothing.toOctaveDivider();
        return octaveDivider;
    }

    public void setSmoothing( final Smoothing smoothing ) {
        setTextValue( smoothing.toPresentationString() );
    }

    public void setSmoothingOctaveDivider( final int octaveDivider ) {
        // Sync up the Combo Box drop-list with the current Smoothing value.
        final Smoothing smoothing = Smoothing.fromOctaveDivider( octaveDivider );
        setSmoothing( smoothing );
    }

}
