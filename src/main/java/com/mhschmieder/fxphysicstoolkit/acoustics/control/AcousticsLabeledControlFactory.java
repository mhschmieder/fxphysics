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
import com.mhschmieder.fxguitoolkit.SceneGraphUtilities;
import com.mhschmieder.fxguitoolkit.control.XToggleButton;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class AcousticsLabeledControlFactory {

    // TODO: Review whether this is still correct, now that we have to package
    // all non-Java resource files in a separate hierarchy from the Java package
    // for the source code.
    @SuppressWarnings("nls") public static final String BUNDLE_NAME =
                                                                    "com.mhschmieder.fxphysicstoolkit.acoustics.action.AcousticsActionLabels";

    /**
     * The default constructor is disabled, as this is a static factory class.
     */
    private AcousticsLabeledControlFactory() {}

    @SuppressWarnings("nls")
    public static Label getSplRangeLabel( final ClientProperties clientProperties ) {
        return com.mhschmieder.fxguitoolkit.SceneGraphUtilities
                .getLabeledLabel( clientProperties, BUNDLE_NAME, "settings", "splRange" );
    }

    @SuppressWarnings("nls")
    public static CheckBox getAutoRangeSplCheckBox( final ClientProperties clientProperties ) {
        return SceneGraphUtilities
                .getLabeledCheckBox( clientProperties, BUNDLE_NAME, "settings", "autoRangeSpl" );
    }

    @SuppressWarnings("nls")
    public static Label getDitheringLabel( final ClientProperties clientProperties ) {
        return SceneGraphUtilities
                .getLabeledLabel( clientProperties, BUNDLE_NAME, "test", "ditheringAmount" );
    }

    @SuppressWarnings("nls")
    public static CheckBox getUseDitheringCheckBox( final ClientProperties clientProperties ) {
        return SceneGraphUtilities
                .getLabeledCheckBox( clientProperties, BUNDLE_NAME, "test", "useDithering" );
    }

    public static XToggleButton getPolarityToggleButton( final boolean applyAspectRatio,
                                                         final boolean selected ) {
        final String selectedText = "Reversed"; //$NON-NLS-1$
        final String deselectedText = "Normal"; //$NON-NLS-1$
        final String tooltipText = "Click to Toggle Polarity Status Between Normal and Reversed"; //$NON-NLS-1$

        // NOTE: JavaFX CSS automatically darkens unselected buttons, and
        // auto-selects the foreground for text fill; we use a custom fill.
        final XToggleButton toggleButton = new XToggleButton( selectedText,
                                                              deselectedText,
                                                              tooltipText,
                                                              "polarity-toggle", //$NON-NLS-1$
                                                              applyAspectRatio,
                                                              3.0d,
                                                              false,
                                                              selected );

        return toggleButton;
    }

    public static XToggleButton getMuteToggleButton( final boolean applyAspectRatio,
                                                     final boolean selected ) {
        final String selectedText = "Muted"; //$NON-NLS-1$
        final String deselectedText = "Mute"; //$NON-NLS-1$
        final String tooltipText = "Click to Toggle Mute Status Between Muted and Unmuted"; //$NON-NLS-1$

        // NOTE: JavaFX CSS automatically darkens unselected buttons, and
        // auto-selects the foreground for text fill; we use a custom fill.
        final XToggleButton toggleButton = new XToggleButton( selectedText,
                                                              deselectedText,
                                                              tooltipText,
                                                              "mute-toggle", //$NON-NLS-1$
                                                              applyAspectRatio,
                                                              3.0d,
                                                              false,
                                                              selected );

        return toggleButton;
    }

}
