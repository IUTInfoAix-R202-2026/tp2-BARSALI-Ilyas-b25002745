package fr.univ_amu.iut.exercice4;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AireTriangle {

  private final IntegerProperty x1 = new SimpleIntegerProperty(0);
  private final IntegerProperty y1 = new SimpleIntegerProperty(0);
  private final IntegerProperty x2 = new SimpleIntegerProperty(0);
  private final IntegerProperty y2 = new SimpleIntegerProperty(0);
  private final IntegerProperty x3 = new SimpleIntegerProperty(0);
  private final IntegerProperty y3 = new SimpleIntegerProperty(0);

  private final DoubleProperty area = new SimpleDoubleProperty(0);

  private StringExpression output;

  public AireTriangle() {
    createBinding();
  }

  private void createBinding() {
    NumberBinding determinant =
        x1.multiply(y2)
            .subtract(x1.multiply(y3))
            .add(x2.multiply(y3))
            .subtract(x2.multiply(y1))
            .add(x3.multiply(y1))
            .subtract(x3.multiply(y2));

    area.bind(
        Bindings.when(determinant.greaterThanOrEqualTo(0))
            .then(determinant.divide(2.0))
            .otherwise(determinant.negate().divide(2.0)));

    output =
        Bindings.format("P1(%d,%d) P2(%d,%d) P3(%d,%d) => aire = %s", x1, y1, x2, y2, x3, y3, area);
  }

  void printResult() {
    System.out.println(output.get());
  }

  public void setP1(int x, int y) {
    x1.set(x);
    y1.set(y);
  }

  public void setP2(int x, int y) {
    x2.set(x);
    y2.set(y);
  }

  public void setP3(int x, int y) {
    x3.set(x);
    y3.set(y);
  }

  public int getX1() {
    return x1.get();
  }

  public void setX1(int v) {
    x1.set(v);
  }

  public IntegerProperty x1Property() {
    return x1;
  }

  public int getY1() {
    return y1.get();
  }

  public void setY1(int v) {
    y1.set(v);
  }

  public IntegerProperty y1Property() {
    return y1;
  }

  public int getX2() {
    return x2.get();
  }

  public void setX2(int v) {
    x2.set(v);
  }

  public IntegerProperty x2Property() {
    return x2;
  }

  public int getY2() {
    return y2.get();
  }

  public void setY2(int v) {
    y2.set(v);
  }

  public IntegerProperty y2Property() {
    return y2;
  }

  public int getX3() {
    return x3.get();
  }

  public void setX3(int v) {
    x3.set(v);
  }

  public IntegerProperty x3Property() {
    return x3;
  }

  public int getY3() {
    return y3.get();
  }

  public void setY3(int v) {
    y3.set(v);
  }

  public IntegerProperty y3Property() {
    return y3;
  }

  public double getArea() {
    return area.get();
  }

  public DoubleProperty areaProperty() {
    return area;
  }

  public static void main(String[] args) {
    AireTriangle t = new AireTriangle();
    t.printResult();
    t.setP1(0, 1);
    t.setP2(6, 0);
    t.setP3(4, 3);
    t.printResult();
    t.setP1(1, 0);
    t.setP2(2, 2);
    t.setP3(0, 1);
    t.printResult();
  }
}
