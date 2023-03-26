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
 * This file is part of the FxPhysicsToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * FxPhysicsToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxphysicstoolkit
 */
package com.mhschmieder.fxphysicstoolkit.action;

import java.util.Arrays;
import java.util.Collection;

import org.controlsfx.control.action.Action;

import com.mhschmieder.commonstoolkit.util.ClientProperties;
import com.mhschmieder.fxguitoolkit.action.FileActions;
import com.mhschmieder.fxguitoolkit.action.LabeledActionFactory;
import com.mhschmieder.fxguitoolkit.action.SettingsActions;
import com.mhschmieder.fxguitoolkit.action.ToolsActions;
import com.mhschmieder.fxguitoolkit.action.XAction;
import com.mhschmieder.fxguitoolkit.action.XActionGroup;

import javafx.scene.paint.Color;

/**
 * This is a struct-like container for actions used by Natural Environment.
 */
public final class NaturalEnvironmentActions {

    public FileActions     _fileActions;
    public SettingsActions _settingsActions;
    public ToolsActions    _toolsActions;

    public XAction         _useAirAttenuationAction;
    public XAction         _resetAction;
    
    protected final boolean _vectorGraphicsSupported;

    public NaturalEnvironmentActions( final ClientProperties pClientProperties,
                                      final boolean vectorGraphicsSupported ) {
        _vectorGraphicsSupported = vectorGraphicsSupported;
        
        _fileActions = new FileActions( pClientProperties );
        _settingsActions = new SettingsActions( pClientProperties );
        _toolsActions = new ToolsActions( pClientProperties );

        _useAirAttenuationAction = PhysicsLabeledActionFactory
                .getUseAirAttenuationAction( pClientProperties );

        _resetAction = LabeledActionFactory.getResetAction( pClientProperties );

        // The tool tip for "Reset" is unique per context so isn't in the
        // locale-sensitive resources for the generic action lookup.
        _resetAction.setLongText( "Reset Natural Environment to Default Values" ); //$NON-NLS-1$
    }

    public Collection< Action > getBackgroundColorChoiceCollection() {
        // Forward this method to the Settings actions container.
        return _settingsActions.getBackgroundColorChoiceCollection();
    }

    public Collection< Action > getExportActionCollection() {
        // Forward this method to the File actions container.
        return _fileActions.getExportActionCollection( true, false );
    }

    public Collection< Action > getFileActionCollection( final ClientProperties pClientProperties ) {
        // Forward this method to the File actions container.
        // TODO: Enable standard Vector Graphics Export for XT.
        return _fileActions
                .getFileActionCollection( pClientProperties, _vectorGraphicsSupported, false );
    }

    public Collection< Action > getNaturalEnvironmentMenuBarActionCollection( final ClientProperties pClientProperties ) {
        // TODO: Enable standard Vector Graphics Export for XT.
        final XActionGroup fileActionGroup = LabeledActionFactory
                .getFileActionGroup( pClientProperties, _fileActions, _vectorGraphicsSupported, false );

        final XActionGroup settingsActionGroup = LabeledActionFactory
                .getSettingsActionGroup( pClientProperties, _settingsActions, true );

        final XActionGroup toolsActionGroup = LabeledActionFactory
                .getToolsActionGroup( pClientProperties, _toolsActions );

        final Collection< Action > naturalEnvironmentMenuBarActionCollection = Arrays
                .asList( fileActionGroup, settingsActionGroup, toolsActionGroup );

        return naturalEnvironmentMenuBarActionCollection;
    }

    public String getSelectedBackgroundColorName() {
        // Forward this method to the Settings actions container.
        return _settingsActions.getSelectedBackgroundColorName();
    }

    public Collection< Action > getSettingsActionCollection( final ClientProperties pClientProperties ) {
        // Forward this method to the File actions container.
        return _settingsActions.getSettingsActionCollection( pClientProperties, true );
    }

    public Collection< Action > getToolsActionCollection( final ClientProperties pClientProperties ) {
        // Forward this method to the Tools actions container.
        return _toolsActions.getToolsActionCollection( pClientProperties );
    }

    public Collection< Action > getWindowSizeActionCollection() {
        // Forward this method to the Settings actions container.
        return _settingsActions.getWindowSizeActionCollection( true );
    }

    public boolean isUseAirAttenuation() {
        return _useAirAttenuationAction.isSelected();
    }

    public Color selectBackgroundColor( final String backgroundColorName ) {
        // Forward this method to the Settings actions container.
        return _settingsActions.selectBackgroundColor( backgroundColorName );
    }

    public void setUseAirAttenuation( final boolean useAirAttenuation ) {
        _useAirAttenuationAction.setSelected( useAirAttenuation );
    }
}
