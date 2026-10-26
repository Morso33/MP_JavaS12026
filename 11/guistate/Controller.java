package memento.guistate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model model;
    private Gui gui;
    private ObservableList<IMemento> history;
    private List<IMemento> redoList;

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.history = FXCollections.observableArrayList();
        this.redoList = new ArrayList<>();
    }

    public void setOption(int optionNumber, int choice) {
        saveToHistory();
        model.setOption(optionNumber, choice);
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        saveToHistory();
        model.setIsSelected(isSelected);
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        if (!history.isEmpty()) {
            System.out.println("Memento found in history");
            redoList.add(model.createMemento());
            IMemento previousState = history.remove(history.size() - 1);
            model.restoreState(previousState);
            gui.updateGui();
        }
    }

    public void redo() {
        if (!redoList.isEmpty()) {
            System.out.println("Memento found in redo list");
            history.add(model.createMemento());
            IMemento nextState = redoList.remove(redoList.size() - 1);
            model.restoreState(nextState);
            gui.updateGui();
        }
    }

    public void restoreFromHistory(IMemento memento) {
        saveToHistory();
        model.restoreState(memento);
        gui.updateGui();
    }

    public ObservableList<IMemento> getHistory() {
        return history;
    }

    private void saveToHistory() {
        IMemento currentState = model.createMemento();
        history.add(currentState);
        redoList.clear();
    }
}
