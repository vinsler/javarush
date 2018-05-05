package com.javarush.task.task25.task2503;

import java.util.LinkedList;
import java.util.List;

public enum Column implements Columnable { // это неявный Class из 4 перечислений и 2 полей.
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;
    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */

    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;
            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>(); // список столбцов 4 + 2
        Column[] array = Column.values(); // создаем массив столбцов равный значению столбцов
        int minEl = 0; // назначаем минимальный элемент
        for (int i = 0; i < realOrder.length; i++) { // пройти нужно все элементы
            for (int j = 0; j < realOrder.length; j++) { // проходим для поиска минимального
                int order = realOrder[j]; // устанавливаем текущий
                if (order != -1) { // если его нужно отображать
                    if (order == minEl) { // и если он подходит под минимальный
                        result.add(array[j]); // добавляем
                        minEl++; // увеличиваем минимальный элемент
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getColumnName() {
        return this.columnName;
    }

    @Override
    public boolean isShown() {
        return realOrder[ordinal()] != -1;
    }

    @Override
    public void hide() {
        int Index = realOrder[ordinal()];
        realOrder[ordinal()] = -1;
        for (int i = 0; i < realOrder.length; i++) {
            if (realOrder[i] > Index) {
                realOrder[i]--;
            }
        }
    }
}