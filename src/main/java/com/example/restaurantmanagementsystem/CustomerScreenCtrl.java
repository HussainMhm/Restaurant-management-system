package com.example.restaurantmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.restaurantmanagementsystem.Kitchen.foodList;
import static com.example.restaurantmanagementsystem.Kitchen.orderList;

public class CustomerScreenCtrl implements Initializable {
    @FXML
    private Button backBtn;
    @FXML
    private Button menuBtn;
    @FXML
    private Button galleryBtn;
    @FXML
    private TableView<Order> orderQueueTable;
    @FXML
    private TableColumn<Order, String> peopleOrdersColumn;

    @FXML
    private TableView<Food> mealsStackTable;
    @FXML
    private TableColumn<Food, String> foodNameColumn;
    @FXML
    private TableColumn<Food, Float> foodPriceColumn;

    Stack stack = new Stack();
    ObservableList<Food> stackList = FXCollections.observableArrayList();

    public void addFoodToStack(){
        for (Food food : foodList)
            stack.push(food);

        int stackSize = stack.getSize();
        for (int i = 0; i < stackSize; i++)
            stackList.add(stack.pop());
    }

    Queue queue = new Queue();
    ObservableList<Order> queueList = FXCollections.observableArrayList();

    public void addOrdersToQueue(){
        for (Order order : orderList){
            queue.enqueue(order);
            queueList.add(queue.dequeue());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addFoodToStack();
        addOrdersToQueue();

        foodNameColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
        foodPriceColumn.setCellValueFactory(new PropertyValueFactory<Food, Float>("price"));
        peopleOrdersColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("customer"));

        mealsStackTable.setItems(stackList);
        orderQueueTable.setItems(queueList);
    }

    public void navigateMenuPage(ActionEvent event){
        Initialize.navigate(event, "Menu.fxml");
    }

    public void navigateGalleryPage(ActionEvent event){
        Initialize.navigate(event, "Gallery.fxml");
    }

    public void navigateBack(ActionEvent event){
        Initialize.navigate(event, "AdminDashboard.fxml");
    }
}