package com.example.demoJavafx.estructurasDeDatos.ArbolAVL;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.demoJavafx.excepciones.DuplicateElement;
import com.example.demoJavafx.excepciones.NonexistentElement;
import com.example.demoJavafx.excepciones.VoidLevel;
class ArbolAVLTest {
    @Test
    void getRaiz() {
        ArbolAVL<String> a1 = new ArbolAVL<>("hola");
        assertEquals("hola", a1.getRaiz().getDato());
    }

    @Test
    void getSubArbolIzquierda() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>(4);
        assertNull(a1.getSubArbolIzquierda());
        try {
            a1.add(3);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        ArbolAVL<Integer> a2 = a1.getSubArbolIzquierda();
        assertEquals(a2.getRaiz().getDato(), a1.getRaiz().getNodoIzq().getDato());
    }

    @Test
    void getSubArbolDerecha() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>(4);
        assertNull(a1.getSubArbolDerecha());
        try {
            a1.add(6);
            a1.add(8);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        ArbolAVL<Integer> a2 = a1.getSubArbolDerecha();
        assertEquals(8, a2.getRaiz().getDato());
    }

    @Test
    void add() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>();
        try {
            a1.add(40);
            a1.add(20);
            a1.add(50);
            a1.add(60);
            a1.add(52);
            a1.add(10);
            a1.add(5);
            a1.add(61);
            a1.add(62);
            a1.add(3);
            a1.add(4);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertThrows(DuplicateElement.class,()-> a1.add(20));
        assertEquals(40,a1.getRaiz().getDato());
        assertEquals(11, a1.getListaOrdenCentral().getNumeroElementos());
    }

    @Test
    void del() {
        ArbolAVL<Integer> arbolAVL = new ArbolAVL<>();
        try {
            arbolAVL.del(8);
        } catch (NonexistentElement ex) {
            ex.printStackTrace();
        }
        Integer[] listaDatosAñadir = new Integer[]{30, 60, 10, 50, 40, 12, 20, 45, 42, 6, 59, 63};
        int contador1 = 0;
        while (listaDatosAñadir.length > contador1) {
            try {
                arbolAVL.add(listaDatosAñadir[contador1]);
            } catch (DuplicateElement ex) {
                ex.printStackTrace();
            }
            contador1++;
        }
        Integer[] listaDatosBorrar = new Integer[]{5, 65, 30, 42, 6, 59, 63, 20, 12};
        int contador2 = 0;
        while (listaDatosBorrar.length > contador2) {
            try {
                arbolAVL.del(listaDatosBorrar[contador2]);
            } catch (NonexistentElement ex) {
                ex.printStackTrace();
            }
            contador2++;
        }
    }

    @Test
    void getGradoMaximo() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>();
        assertNull(a1.getGradoMaximo());
        try {
            a1.add(40);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertEquals(0, a1.getGradoMaximo());
        try {
            a1.add(41);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertEquals(1, a1.getGradoMaximo());
        try {
            a1.add(42);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertEquals(2, a1.getGradoMaximo());
    }

    @Test
    void getAltura() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>();
        assertEquals(0, a1.getAltura());
        try {
            a1.add(40);
            a1.add(41);
            a1.add(39);
            a1.add(38);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertEquals(3, a1.getAltura());
    }

    @Test
    void getListaDatosNivel() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>();
        try {
            a1.add(40);
            a1.add(41);
            a1.add(39);
            a1.add(38);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        try {
            a1.getListaDatosNivel(980);
        } catch (VoidLevel ex) {
            ex.printStackTrace();
        }
        try {
            assertEquals(1, a1.getListaDatosNivel(a1.getAltura()).getNumeroElementos());
            assertEquals(38, a1.getListaDatosNivel(3).getPrimero().getData());
        } catch (VoidLevel ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void getCamino() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>();
        try {
            a1.add(40);
            a1.add(60);
            a1.add(20);
            a1.add(35);
            a1.add(34);
            a1.add(33);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        try{
            assertEquals(3,a1.getCamino(35).getNumeroElementos());
            assertEquals(40,a1.getCamino(35).getPrimero().getSiguiente().getData());
            a1.getCamino(70);
        } catch (NonexistentElement ex){
            ex.printStackTrace();
        }
    }

    @Test
    void isArbolCasiCompleto() throws DuplicateElement, NonexistentElement, VoidLevel {
        ArbolAVL<Integer> a1 = new ArbolAVL<>();
        try {
            a1.add(40);
            a1.add(30);
            a1.add(45);
            a1.add(29);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertTrue(a1.isArbolCasiCompleto());
        try{
            a1.add(46);
        } catch (DuplicateElement ex){
            ex.printStackTrace();
        }
        assertFalse(a1.isArbolCasiCompleto());
        try{
            a1.del(46);
            a1.add(31);
        } catch (DuplicateElement | NonexistentElement ex){
            ex.printStackTrace();
        }
        assertTrue(a1.isArbolCasiCompleto());
    }

    @Test
    void isArbolHomogeneo() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>();
        try {
            a1.add(40);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertTrue(a1.isArbolHomogeneo());
        try {
            a1.add(41);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertTrue(a1.isArbolHomogeneo());
        try{
            a1.del(41);
            a1.add(39);
        } catch(DuplicateElement | NonexistentElement ex){
            ex.printStackTrace();
        }
        assertTrue(a1.isArbolHomogeneo());
        try{
            a1.add(38);
            a1.add(37);
        } catch(DuplicateElement ex){
            ex.printStackTrace();
        }
        assertFalse(a1.isArbolHomogeneo());

    }

    @Test
    void isArbolCompleto() throws DuplicateElement, NonexistentElement, VoidLevel{
        ArbolAVL<Integer> a1 = new ArbolAVL<>();
        try {
            a1.add(40);
            a1.add(39);
            a1.add(41);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertTrue(a1.isArbolCompleto());
        try {
            a1.add(42);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertFalse(a1.isArbolCompleto());
    }

    @Test
    void getListaOrdenCentral() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>(40);
        try {
            a1.add(10);
            a1.add(50);
            a1.add(9);
            a1.add(11);
            a1.add(49);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertEquals(6, a1.getListaOrdenCentral().getNumeroElementos());
        assertEquals(50, a1.getListaOrdenCentral().getElemento(5).getData());
        assertEquals(10, a1.getListaOrdenCentral().getElemento(1).getData());

    }

    @Test
    void getListaPreOrden() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>(40);
        try {
            a1.add(10);
            a1.add(50);
            a1.add(9);
            a1.add(11);
            a1.add(49);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertEquals(6, a1.getListaOrdenCentral().getNumeroElementos());
        assertEquals(40, a1.getListaPreOrden().getPrimero().getData());
        assertEquals(49, a1.getListaPreOrden().getUltimo().getData());

    }

    @Test
    void getListaPostOrden() {
        ArbolAVL<Integer> a1 = new ArbolAVL<>(40);
        try {
            a1.add(10);
            a1.add(50);
            a1.add(9);
            a1.add(11);
            a1.add(49);
        } catch (DuplicateElement ex) {
            System.out.println("Tratamos excepcion");
        }
        assertEquals(6, a1.getListaOrdenCentral().getNumeroElementos());
        assertEquals(9, a1.getListaPostOrden().getPrimero().getData());
        assertEquals(40, a1.getListaPostOrden().getUltimo().getData());

    }
}