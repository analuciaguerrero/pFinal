package com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NodoTest {
    @Test
    public void testConstructorWithData() {
        // Arrange
        Integer data = 10;

        // Act
        Nodo<Integer> nodo = new Nodo<>(data);

        // Assert
        assertEquals(data, nodo.getDato());
        assertNull(nodo.getIzquierda());
        assertNull(nodo.getDerecha());
    }

    @Test
    public void testConstructorWithChildrenAndData() {
        // Arrange
        Integer data = 10;
        Nodo<Integer> leftChild = new Nodo<>(5);
        Nodo<Integer> rightChild = new Nodo<>(15);

        // Act
        Nodo<Integer> nodo = new Nodo<>(rightChild, leftChild, data);

        // Assert
        assertEquals(data, nodo.getDato());
        assertEquals(leftChild, nodo.getIzquierda());
        assertEquals(rightChild, nodo.getDerecha());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Integer data = 10;
        Nodo<Integer> nodo = new Nodo<>();

        // Act
        nodo.setDato(data);

        // Assert
        assertEquals(data, nodo.getDato());

        // Act
        Nodo<Integer> leftChild = new Nodo<>(5);
        nodo.setIzquierda(leftChild);

        // Assert
        assertEquals(leftChild, nodo.getIzquierda());

        // Act
        Nodo<Integer> rightChild = new Nodo<>(15);
        nodo.setDerecha(rightChild);

        // Assert
        assertEquals(rightChild, nodo.getDerecha());
    }
}