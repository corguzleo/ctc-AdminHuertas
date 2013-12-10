/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.test;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.ComboBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * Sample code to demonstrate the {@link ComboBoxCellFactory} API
 * for the ListView, TreeView and TableView controls.
 * 
 * @author Jonathan Giles
 */
public class ComboBoxCellSample extends Application {
    
    private final static ObservableList<String> namesChoiceList;
    private final static ObservableList<String> namesList;
    
    private final static ObservableList<Person> personsList;
    
    static {
        namesChoiceList = FXCollections.observableArrayList("Jenny", "Billy", "Timmy");
        namesList = FXCollections.observableArrayList(namesChoiceList);
        
        personsList = Person.getTestList();
    }

    public static void main(String[] args) {
        launch(ComboBoxCellSample.class, args);
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("ComboBoxCell Samples");
        final Scene scene = new Scene(new Group(), 875, 700);
        scene.setFill(Color.LIGHTGRAY);
        Group root = (Group)scene.getRoot();
        
        root.getChildren().add(getContent(scene));

        stage.setScene(scene);
        stage.show();
    }
    
    public Node getContent(Scene scene) {
        // TabPane
        final TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setPrefWidth(scene.getWidth());
        tabPane.setPrefHeight(scene.getHeight());
        
        tabPane.prefWidthProperty().bind(scene.widthProperty());
        tabPane.prefHeightProperty().bind(scene.heightProperty());
        
        // list view examples
        Tab listViewTab = new Tab("ListView");
        buildListViewTab(listViewTab);
        tabPane.getTabs().add(listViewTab);
        
        // tree view examples
        Tab treeViewTab = new Tab("TreeView");
        buildTreeViewTab(treeViewTab);
        tabPane.getTabs().add(treeViewTab);
        
        // table view examples
        Tab tableViewTab = new Tab("TableView");
        buildTableViewTab(tableViewTab);
        tabPane.getTabs().add(tableViewTab);
        
        return tabPane;
    }
        
    private void buildListViewTab(Tab tab) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setHgap(5);
        grid.setVgap(5);

        // create a simple String listview
        final ListView<String> listView = new ListView<String>();
        listView.setItems(namesList);
        listView.setEditable(true);
        listView.setCellFactory(ComboBoxListCell.forListView(namesChoiceList));
        grid.add(listView, 0, 0);
        GridPane.setVgrow(listView, Priority.ALWAYS);
        
        // create a Person listview
        final ListView<Person> listView2 = new ListView<Person>();
        listView2.setEditable(true);
        listView2.setItems(personsList);
        listView2.setCellFactory(ComboBoxListCell.forListView(Person.getTestList()));
        grid.add(listView2, 1, 0);
        GridPane.setVgrow(listView2, Priority.ALWAYS);
        
        // create a view on to the persons list
        final ListView<Person> listView3 = new ListView<Person>();
        listView3.setItems(personsList);
        grid.add(listView3, 2, 0);
        GridPane.setVgrow(listView3, Priority.ALWAYS);
        
        tab.setContent(grid);
    }
    
    private void buildTreeViewTab(Tab tab) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setHgap(5);
        grid.setVgap(5);

        // create a simple String treeview
        final TreeItem<String> root = TreeModels.getFamiliesTree();
        root.setExpanded(true);
        
        // create the treeView
        final TreeView<String> treeView = new TreeView<String>();
        treeView.setRoot(root);
        
        // set the cell factory
        treeView.setEditable(true);
        treeView.setCellFactory(ComboBoxTreeCell.forTreeView(namesChoiceList));
        
        grid.add(treeView, 1, 0);
        GridPane.setVgrow(treeView, Priority.ALWAYS);
        
        tab.setContent(grid);
    }
    
    private void buildTableViewTab(Tab tab) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setHgap(5);
        grid.setVgap(5);

        // create a simple String tableview
        TableColumn<String, String> nameColumn = new TableColumn<String, String>("Name");
        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String,String>, ObservableValue<String>>() {
            @Override public ObservableValue<String> call(CellDataFeatures<String, String> cdf) {
                return new ReadOnlyObjectWrapper<String>(cdf.getValue());
            }
        });
        nameColumn.setCellFactory(ComboBoxTableCell.<String, String>forTableColumn(namesChoiceList));
        TableView<String> tableView = new TableView<String>();
        tableView.setItems(namesList);
        tableView.setEditable(true);
        tableView.getColumns().setAll(nameColumn);
        grid.add(tableView, 0, 0);
        GridPane.setVgrow(tableView, Priority.ALWAYS);
        
        
        
        // create a Person tableview
        TableColumn<Person, Boolean> invitedColumn = new TableColumn<Person, Boolean>("Invited");
        invitedColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,Boolean>, ObservableValue<Boolean>>() {
            @Override public ObservableValue<Boolean> call(CellDataFeatures<Person, Boolean> cdf) {
                return cdf.getValue().telecommuterProperty();
            }
        });
        invitedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(invitedColumn));
        
        final TableColumn<Person,String> firstNameColumn = new TableColumn<Person,String>("First");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
        firstNameColumn.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn(namesChoiceList));
        
        final TableColumn<Person,String> lastNameColumn = new TableColumn<Person,String>("Last");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
        lastNameColumn.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn(namesChoiceList));
        
        TableColumn<Person,String> nameColumn2 = new TableColumn<Person,String>("Name");
        nameColumn2.getColumns().setAll(firstNameColumn, lastNameColumn);

        TableView<Person> tableView2 = new TableView<Person>();
        tableView2.setEditable(true);
        tableView2.setItems(personsList);
        tableView2.getColumns().setAll(invitedColumn, nameColumn2);

//        EventHandler<TableView.EditEvent<String>> nameEditHandler = new EventHandler<TableView.EditEvent<String>>() {
//            @Override public void handle(TableView.EditEvent<String> e) {
//                Person p = e.getRowValue(); 
//                
//                String newValue = e.getNewValue();
//                if (e.getTableColumn().equals(firstNameColumn)) {
//                    p.setFirstName(newValue);
//                } else if (e.getTableColumn().equals(lastNameColumn)) {
//                    p.setLastName(newValue);
//                }
//            }
//        };
//        
//        firstNameColumn.setOnEditCommit(nameEditHandler);
//        lastNameColumn.setOnEditCommit(nameEditHandler);
        
        grid.add(tableView2, 1, 0);
        GridPane.setVgrow(tableView2, Priority.ALWAYS);
        
        tab.setContent(grid);
    }
}