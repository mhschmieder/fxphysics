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
import com.mhschmieder.physicstoolkit.acoustics.RelativeBandwidth;

/**
 * The Relative Bandwidth Combo Box is a chooser for bandwidth ranges generally
 * considered useful for acoustic analysis modes, meaning divisors of 1/3 octave
 * as well as full octave. We do not yet support multi-octave bandwidth.
 */
public final class RelativeBandwidthSelector extends TextSelector {

    // Default Relative Bandwidth, for best "out of box" experience.
    public static final String    RELATIVE_BANDWIDTH_DEFAULT = RelativeBandwidth.defaultValue()
            .toPresentationString();

    private static final String[] RELATIVE_BANDWIDTHS        =
                                                      new String[] {
                                                                     RelativeBandwidth.ONE_OCTAVE
                                                                             .toPresentationString(),
                                                                     RelativeBandwidth.THIRD_OCTAVE
                                                                             .toPresentationString(),
                                                                     RelativeBandwidth.SIXTH_OCTAVE
                                                                             .toPresentationString(),
                                                                     RelativeBandwidth.TWELTH_OCTAVE
                                                                             .toPresentationString(),
                                                                     RelativeBandwidth.TWENTYFOURTH_OCTAVE
                                                                             .toPresentationString(),
                                                      // RelativeBandwidth.FORTYEIGHTH_OCTAVE.toPresentationString()
                                                      };

    public RelativeBandwidthSelector( final ClientProperties pClientProperties,
                                      final boolean pToolbarContext ) {
        // Always call the superclass constructor first!
        super( pClientProperties,
               "Relative Bandwidth", //$NON-NLS-1$
               pToolbarContext,
               false,
               false,
               RELATIVE_BANDWIDTHS,
               RELATIVE_BANDWIDTH_DEFAULT );
    }

    public RelativeBandwidth getRelativeBandwidth() {
        return RelativeBandwidth.fromPresentationString( getTextValue() );
    }

    public void setRelativeBandwidth( final RelativeBandwidth relativeBandwidth ) {
        setTextValue( relativeBandwidth.toPresentationString() );
    }

}
