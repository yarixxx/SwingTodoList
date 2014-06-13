package com.todo;

import java.awt.event.ItemEvent;
import java.util.*;

public class TodoList {
    private List<TodoItem> todos = new ArrayList<TodoItem>();

    public void addItem(String item) {
        TodoItem newItem = new TodoItem(item);

        newItem.setStatus(Status.WAITING);
        todos.add(newItem);
    }

    public String getItemStatus(int item) {
        return todos.get(item).getStatus().getTitle();
    }

    public int getSize() {
        return todos.size();
    }

    public List<TodoItem> getItems() {
        return Collections.unmodifiableList(todos);
    }

    public void setStatus(int selectedItem, Status status) {
        todos.get(selectedItem).setStatus(status);
    }

    public String getItem(int index) {
        return todos.get(index).name;
    }

    public void remove(int rowindex) {
        todos.remove(rowindex);
    }

    private class TodoItem {
        private String name;
        private Status status;

        public TodoItem(String item) {
            this.name = item;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Status getStatus() {
            return status;
        }
    }
}