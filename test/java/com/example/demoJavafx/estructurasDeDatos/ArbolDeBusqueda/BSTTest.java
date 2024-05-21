package com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BSTTest {
    public void testConstructorWithNodes() {
        // Arrange
        Nodo<Integer> leftChild = new Nodo<>(5);
        Nodo<Integer> rightChild = new Nodo<>(15);
        Nodo<Integer> raiz = new Nodo<>(10);
        raiz.setIzquierda(leftChild);
        raiz.setDerecha(rightChild);
        BST<Integer> bst = new BST<>(raiz);

        // Act
        int gradoRaiz = bst.getGradoN(raiz);
        int gradoIzquierda = bst.getGradoN(leftChild);
        int gradoDerecha = bst.getGradoN(rightChild);

        // Assert
        assertEquals(2, gradoRaiz);
        assertEquals(1, gradoIzquierda);
        assertEquals(1, gradoDerecha);
    }
    @Test
    public void testAdd() {
        // Arrange
        BST<Integer> bst = new BST<>();

        // Act
        bst.add(10);
        bst.add(5);
        bst.add(15);

        // Assert
        assertEquals(10, (int) bst.getRaiz().getDato());
        assertEquals(5, (int) bst.getRaiz().getIzquierda().getDato());
        assertEquals(15, (int) bst.getRaiz().getDerecha().getDato());
    }

    @Test
    public void testGetCamino() {
        // Arrange
        Nodo<Integer> leftChild = new Nodo<>(5);
        Nodo<Integer> rightChild = new Nodo<>(15);
        Nodo<Integer> raiz = new Nodo<>(rightChild, leftChild, 10);
        BST<Integer> bst = new BST<>(raiz);

        // Act
        ListaDoblementeEnlazada<Nodo<Integer>> camino = bst.getCamino(15);

        // Assert
        assertNotNull(camino);
        assertEquals(3, camino.getNumeroElementos());
    }

    @Test
    public void testPreOrden() {
        // Arrange
        Nodo<Integer> leftChild = new Nodo<>(5);
        Nodo<Integer> rightChild = new Nodo<>(15);
        Nodo<Integer> raiz = new Nodo<>(rightChild, leftChild, 10);
        BST<Integer> bst = new BST<>(raiz);

        // Act
        ListaDoblementeEnlazada<Integer> preOrden = bst.preOrden();

        // Assert
        assertEquals(3, preOrden.getNumeroElementos());
    }

    @Test
    public void testOrdenCentral() {
        // Arrange
        Nodo<Integer> leftChild = new Nodo<>(5);
        Nodo<Integer> rightChild = new Nodo<>(15);
        Nodo<Integer> raiz = new Nodo<>(rightChild, leftChild, 10);
        BST<Integer> bst = new BST<>(raiz);

        // Act
        ListaDoblementeEnlazada<Integer> ordenCentral = bst.ordenCentral();

        // Assert
        assertEquals(3, ordenCentral.getNumeroElementos());
    }

    @Test
    public void testPostOrden() {
        // Arrange
        Nodo<Integer> leftChild = new Nodo<>(5);
        Nodo<Integer> rightChild = new Nodo<>(15);
        Nodo<Integer> raiz = new Nodo<>(rightChild, leftChild, 10);
        BST<Integer> bst = new BST<>(raiz);

        // Act
        ListaDoblementeEnlazada<Integer> postOrden = bst.postOrden();

        // Assert
        assertEquals(3, postOrden.getNumeroElementos());
    }
}