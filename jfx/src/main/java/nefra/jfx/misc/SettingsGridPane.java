package nefra.jfx.misc;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import nefra.exceptions.DelLog;

public class SettingsGridPane extends GridPane{
    public SettingsGridPane() {
        getStylesheets().add(getClass().getResource("/nefra/jfx/misc/SettingsGridPane.css").toExternalForm());
        getStyleClass().clear();
        getStyleClass().add("internals");
    }

    private void update()
    {
        int maxRow = 0;
        for(int i = 0; i < getChildren().size(); i++) {
            if(getRowIndex(getChildren().get(i)) > maxRow) maxRow = getRowIndex(getChildren().get(i));
            DelLog.getInstance().Log(getChildren().get(i).getStyle());
        }
        getRowConstraints().clear();
        for (int i = 0; i <= maxRow; i++)
        {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMinHeight(15);
            rowConst.setMaxHeight(50);
            getRowConstraints().add(rowConst);
        }
    }

    /**
     * Adds a label to the gridpane at the specified column,row position.
     * This convenience method will set the gridpane column and row constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param label the label being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     */
    public void add(Label label, int columnIndex, int rowIndex)
    {
        getStyleClass().clear();
        getStyleClass().add("label");
        super.add(label, columnIndex, rowIndex);
        update();
        DelLog.getInstance().Log(label.getFont().toString());
    }

    /**
     * Adds a check box to the gridpane at the specified column,row position.
     * This convenience method will set the gridpane column and row constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param checkBox the check box being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     */
    public void add(CheckBox checkBox, int columnIndex, int rowIndex)
    {
        getStyleClass().clear();
        getStyleClass().add("checkBox");
        super.add(checkBox, columnIndex, rowIndex);
        update();
        DelLog.getInstance().Log(checkBox.getFont().toString());
    }

    /**
     * Adds a text field to the gridpane at the specified column,row position.
     * This convenience method will set the gridpane column and row constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param textField the text field being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     */
    public void add(TextField textField, int columnIndex, int rowIndex)
    {
        getStyleClass().clear();
        getStyleClass().add("textField");
        super.add(textField, columnIndex, rowIndex);
        update();
        DelLog.getInstance().Log(textField.getFont().toString());
    }

    /**
     * Adds a choice box to the gridpane at the specified column,row position.
     * This convenience method will set the gridpane column and row constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param choiceBox the choice box being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     */
    public void add(ChoiceBox choiceBox, int columnIndex, int rowIndex)
    {
        getStyleClass().clear();
        getStyleClass().add("choiceBox");
        super.add(choiceBox, columnIndex, rowIndex);
        update();
        DelLog.getInstance().Log(choiceBox.getStyle());
    }

    /**
     * Adds a buttons to the gridpane at the specified column,row position.
     * This convenience method will set the gridpane column and row constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param button the button being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     */
    public void add(Button button, int columnIndex, int rowIndex)
    {
        getStyleClass().clear();
        getStyleClass().add("buttonClass");
        super.add(button, columnIndex, rowIndex);
        update();
        DelLog.getInstance().Log(button.getFont().toString());
    }

    /**
     * Adds a child to the gridpane at the specified column,row position.
     * This convenience method will set the gridpane column and row constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param node the node being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     */
    @Override
    public void add(Node node, int columnIndex, int rowIndex)
    {
        getStyleClass().clear();
        getStyleClass().add("other");
        super.add(node, columnIndex, rowIndex);
        update();
        DelLog.getInstance().Log(node.getStyle());
    }

    /**
     * Adds a label to the gridpane at the specified column,row position and spans.
     * This convenience method will set the gridpane column, row, and span constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param label the label being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     * @param colspan the number of columns the child's layout area should span
     * @param rowspan the number of rows the child's layout area should span
     */
    public void add(Label label, int columnIndex, int rowIndex, int colspan, int rowspan)
    {
        getStyleClass().clear();
        getStyleClass().add("label");
        super.add(label, columnIndex, rowIndex, colspan, rowspan);
        update();
        DelLog.getInstance().Log(label.getFont().toString());
    }

    /**
     * Adds a check box to the gridpane at the specified column,row position and spans.
     * This convenience method will set the gridpane column, row, and span constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param checkBox the check box being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     * @param colspan the number of columns the child's layout area should span
     * @param rowspan the number of rows the child's layout area should span
     */
    public void add(CheckBox checkBox, int columnIndex, int rowIndex, int colspan, int rowspan)
    {
        getStyleClass().clear();
        getStyleClass().add("checkBox");
        super.add(checkBox, columnIndex, rowIndex, colspan, rowspan);
        update();
        DelLog.getInstance().Log(checkBox.getFont().toString());
    }

    /**
     * Adds a text field to the gridpane at the specified column,row position and spans.
     * This convenience method will set the gridpane column, row, and span constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param textField the text field being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     * @param colspan the number of columns the child's layout area should span
     * @param rowspan the number of rows the child's layout area should span
     */
    public void add(TextField textField, int columnIndex, int rowIndex, int colspan, int rowspan)
    {
        getStyleClass().clear();
        getStyleClass().add("textField");
        super.add(textField, columnIndex, rowIndex, colspan, rowspan);
        update();
        DelLog.getInstance().Log(textField.getFont().toString());
    }

    /**
     * Adds a choice box to the gridpane at the specified column,row position and spans.
     * This convenience method will set the gridpane column, row, and span constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param choiceBox the choice box being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     * @param colspan the number of columns the child's layout area should span
     * @param rowspan the number of rows the child's layout area should span
     */
    public void add(ChoiceBox choiceBox, int columnIndex, int rowIndex, int colspan, int rowspan)
    {
        getStyleClass().clear();
        getStyleClass().add("choiceBox");
        super.add(choiceBox, columnIndex, rowIndex, colspan, rowspan);
        update();
        DelLog.getInstance().Log(choiceBox.getStyle());
    }

    /**
     * Adds a button to the gridpane at the specified column,row position and spans.
     * This convenience method will set the gridpane column, row, and span constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param button the button being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     * @param colspan the number of columns the child's layout area should span
     * @param rowspan the number of rows the child's layout area should span
     */
    public void add(Button button, int columnIndex, int rowIndex, int colspan, int rowspan)
    {
        getStyleClass().clear();
        getStyleClass().add("buttonClass");
        super.add(button, columnIndex, rowIndex, colspan, rowspan);
        update();
        DelLog.getInstance().Log(button.getFont().toString());
    }

    /**
     * Adds a child to the gridpane at the specified column,row position and spans.
     * This convenience method will set the gridpane column, row, and span constraints
     * on the child.
     * This also applies a custom style implemented in CSS
     * @param node the node being added to the gridpane
     * @param columnIndex the column index position for the child within the gridpane, counting from 0
     * @param rowIndex the row index position for the child within the gridpane, counting from 0
     * @param colspan the number of columns the child's layout area should span
     * @param rowspan the number of rows the child's layout area should span
     */
    @Override
    public void add(Node node, int columnIndex, int rowIndex, int colspan, int rowspan)
    {
        getStyleClass().clear();
        getStyleClass().add("other");
        super.add(node, columnIndex, rowIndex, colspan, rowspan);
        update();
        DelLog.getInstance().Log(node.getStyle());
    }
}
