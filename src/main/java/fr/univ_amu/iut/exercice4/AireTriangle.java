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
    // Calcul du déterminant avec l'API fluente JavaFX
    // Formule : x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)
    NumberBinding determinant =
        x1.multiply(y2)
            .subtract(x1.multiply(y3))
            .add(x2.multiply(y3))
            .subtract(x2.multiply(y1))
            .add(x3.multiply(y1))
            .subtract(x3.multiply(y2));

    // Lier area à la valeur absolue du déterminant divisé par 2
    // Bindings.when() remplace Math.abs() de façon réactive
    area.bind(
        Bindings.when(determinant.greaterThanOrEqualTo(0))
            .then(determinant.divide(2.0))
            .otherwise(determinant.negate().divide(2.0)));

    // StringExpression pour afficher les coordonnées et l'aire formatées
    output =
        Bindings.format("P1(%s,%s) P2(%s,%s) P3(%s,%s) => aire = %s", x1, y1, x2, y2, x3, y3, area);
  }

  void printResult() {
    // Affiche la StringExpression output dans la console
    System.out.println(output.get());
  }

  // --- Setters de points (raccourcis pour définir x et y en même temps) ---

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

  // --- Convention JavaBeans pour x1 ---

  public int getX1() {
    return x1.get();
  }

  public void setX1(int value) {
    x1.set(value);
  }

  public IntegerProperty x1Property() {
    return x1;
  }

  // --- Convention JavaBeans pour y1 ---

  public int getY1() {
    return y1.get();
  }

  public void setY1(int value) {
    y1.set(value);
  }

  public IntegerProperty y1Property() {
    return y1;
  }

  // --- Convention JavaBeans pour x2 ---

  public int getX2() {
    return x2.get();
  }

  public void setX2(int value) {
    x2.set(value);
  }

  public IntegerProperty x2Property() {
    return x2;
  }

  // --- Convention JavaBeans pour y2 ---

  public int getY2() {
    return y2.get();
  }

  public void setY2(int value) {
    y2.set(value);
  }

  public IntegerProperty y2Property() {
    return y2;
  }

  // --- Convention JavaBeans pour x3 ---

  public int getX3() {
    return x3.get();
  }

  public void setX3(int value) {
    x3.set(value);
  }

  public IntegerProperty x3Property() {
    return x3;
  }

  // --- Convention JavaBeans pour y3 ---

  public int getY3() {
    return y3.get();
  }

  public void setY3(int value) {
    y3.set(value);
  }

  public IntegerProperty y3Property() {
    return y3;
  }

  // --- Accesseur aire (lecture seule depuis l'extérieur) ---

  public double getArea() {
    return area.get();
  }

  public DoubleProperty areaProperty() {
    return area;
  }

  public static void main(String[] args) {
    AireTriangle t = new AireTriangle();
    t.printResult(); // P1(0,0) P2(0,0) P3(0,0) => aire = 0.0

    t.setP1(0, 1);
    t.setP2(6, 0);
    t.setP3(4, 3);
    t.printResult(); // P1(0,1) P2(6,0) P3(4,3) => aire = 9.0

    t.setP1(1, 0);
    t.setP2(2, 2);
    t.setP3(0, 1);
    t.printResult(); // P1(1,0) P2(2,2) P3(0,1) => aire = 1.5
  }
}
