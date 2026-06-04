package fr.univ_amu.iut.exercice1;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

/** Exercice 1 - Découverte des propriétés JavaFX. */
public class ProprieteSimple {

  private IntegerProperty anIntProperty;
  private InvalidationListener invalidationListener;
  private ChangeListener<Number> changeListener;

  /** Crée la propriété et affiche ses informations sur la sortie standard. */
  void creerPropriete() {

    if (anIntProperty == null) {
      anIntProperty = new SimpleIntegerProperty();
    }

    System.out.println();
    System.out.println("anIntProperty = " + anIntProperty);
    System.out.println("anIntProperty.get() = " + anIntProperty.get());
    System.out.println("anIntProperty.getValue() = " + anIntProperty.getValue());
  }

  /** Ajoute un InvalidationListener, modifie la propriété, puis le retire. */
  void ajouterEtRetirerInvalidationListener() {

    System.out.println();
    System.out.println("Add invalidation listener.");

    this.invalidationListener =
        observable -> System.out.println("The observable has been invalidated.");

    anIntProperty.addListener(invalidationListener);

    System.out.println("setValue() with 1024.");
    anIntProperty.setValue(1024);

    System.out.println("set() with 2105.");
    anIntProperty.set(2105);

    System.out.println("setValue() with 5012.");
    anIntProperty.setValue(5012);

    System.out.println("Remove invalidation listener.");
    anIntProperty.removeListener(invalidationListener);

    System.out.println("set() with 1024.");
    anIntProperty.set(1024);
  }

  /** Ajoute un ChangeListener, modifie la propriété, puis le retire. */
  void ajouterEtRetirerChangeListener() {

    System.out.println();
    System.out.println("Add change listener.");

    this.changeListener =
        (observable, oldValue, newValue) ->
            System.out.println(
                "The observableValue has changed: oldValue = "
                    + oldValue.intValue()
                    + ", newValue = "
                    + newValue.intValue());

    anIntProperty.addListener(changeListener);

    System.out.println("setValue() with 1024.");
    anIntProperty.setValue(1024);

    System.out.println("set() with 2105.");
    anIntProperty.set(2105);

    System.out.println("setValue() with 5012.");
    anIntProperty.setValue(5012);

    System.out.println("Remove change listener.");
    anIntProperty.removeListener(changeListener);

    System.out.println("set() with 1024.");
    anIntProperty.set(1024);
  }

  public int getAnInt() {
    return anIntProperty.get();
  }

  public void setAnInt(int value) {
    if (anIntProperty == null) {
      anIntProperty = new SimpleIntegerProperty();
    }
    anIntProperty.set(value);
  }

  public IntegerProperty anIntProperty() {
    return anIntProperty;
  }

  public static void main(String[] args) {

    ProprieteSimple exemple = new ProprieteSimple();

    exemple.setAnInt(1024);

    exemple.creerPropriete();

    exemple.ajouterEtRetirerInvalidationListener();

    exemple.ajouterEtRetirerChangeListener();
  }
}
