package com.example.demoJavafx.estructurasDeDatos.ArbolAVL;

import com.example.demoJavafx.excepciones.DuplicateElement;
import com.example.demoJavafx.excepciones.NonexistentElement;

public class NodoAVL<TipoDelDato>{
    private TipoDelDato dato;
    private NodoAVL<TipoDelDato> nodoIzq;
    private NodoAVL<TipoDelDato> nodoDch;
    public NodoAVL() {
        this.dato = null;
    }

    public NodoAVL(TipoDelDato dato) {
        this.dato = dato;
    }

    private Integer compararDatos(TipoDelDato a, TipoDelDato b) {
        Comparable dato1 = (Comparable) a;
        Comparable dato2 = (Comparable) b;
        return dato1.compareTo(dato2);
    }

    protected int alturaNodo(NodoAVL<TipoDelDato> nodo) {
        return calcularAlturaRecursivo(nodo);
    }

    private int calcularAlturaRecursivo(NodoAVL<TipoDelDato> nodo) {
        if (nodo == null) {
            return 0;
        } else {
            int alturaIzq = calcularAlturaRecursivo(nodo.nodoIzq);
            int alturaDch = calcularAlturaRecursivo(nodo.nodoDch);
            return Math.max(alturaIzq, alturaDch) + 1;
        }
    }

    public int getGrado() {
        int grado = 0;
        if (this.getNodoDch() != null) {
            grado++;
        }
        if (this.getNodoIzq() != null) {
            grado++;
        }
        return grado;
    }

    protected void del(NodoAVL<TipoDelDato> padre, NodoAVL<TipoDelDato> actual, TipoDelDato a) throws NonexistentElement {
        if (this.compararDatos(a, actual.getDato()) == 1) {
            if (actual.getNodoDch() != null) {
                del(actual, actual.getNodoDch(), a);
                this.equilibrar(actual);
            } else {
                throw (new NonexistentElement("Elemento inexistente"));
            }
        } else if (this.compararDatos(a, actual.getDato()) == -1) {
            if (actual.getNodoIzq() != null) {
                del(actual, actual.getNodoIzq(), a);
                this.equilibrar(actual);
            } else {
                throw (new NonexistentElement("Elemento inexistente"));
            }
        } else {
            if (actual.getGrado() == 0) {
                if (padre.getNodoIzq() != null) {
                    if (compararDatos(padre.getNodoIzq().getDato(), a) == 0) {
                        padre.setNodoIzq(null);
                    } else {
                        padre.setNodoDch(null);
                    }
                } else if (padre.getNodoDch() != null) {
                    if (compararDatos(padre.getNodoDch().getDato(), a) == 0) {
                        padre.setNodoDch(null);
                    }
                }
            } else if (actual.getGrado() == 1) {
                NodoAVL<TipoDelDato> temporal = new NodoAVL<>();
                if (actual.getNodoIzq() != null) {
                    temporal.copiaLigera(actual.getNodoIzq());
                } else {
                    temporal.copiaLigera(actual.getNodoDch());
                }
                actual.copiaLigera(temporal);
            } else {
                NodoAVL<TipoDelDato> nodoDespués = actual.getNodoDch();
                NodoAVL<TipoDelDato> nodoAntDespués = actual;
                while (nodoDespués.getNodoIzq() != null) {
                    nodoAntDespués = nodoDespués;
                    nodoDespués = nodoDespués.getNodoIzq();
                }
                actual.setDato(nodoDespués.getDato());
                del(nodoAntDespués, nodoDespués, nodoDespués.getDato());
                this.equilibrar(nodoAntDespués);
            }

        }
    }

    protected void add(NodoAVL<TipoDelDato> nodo, TipoDelDato dato) throws DuplicateElement {
        Integer comparacion = this.compararDatos(dato, nodo.getDato());
        if (comparacion == 0) {
            throw (new DuplicateElement("Este elemento ya está en el arbol"));
        } else if (comparacion == -1) {
            if (nodo.getNodoIzq() != null) {
                add(nodo.getNodoIzq(), dato);
                this.equilibrar(nodo);
            } else {
                nodo.setNodoIzq(new NodoAVL<>(dato));
            }
        } else {
            if (nodo.getNodoDch() != null) {
                add(nodo.getNodoDch(), dato);
                this.equilibrar(nodo);
            } else {
                nodo.setNodoDch(new NodoAVL<>(dato));
            }
        }
    }

    private void copiaLigera(NodoAVL<TipoDelDato> nodoCopiado) {
        this.dato = nodoCopiado.getDato();
        this.nodoDch = nodoCopiado.getNodoDch();
        this.nodoIzq = nodoCopiado.getNodoIzq();
    }

    private Integer reconocerDesequilibrio() {
        int balance = alturaNodo(this.nodoIzq) - alturaNodo(this.nodoDch);
        if ((balance > 1) && (alturaNodo(this.nodoIzq.nodoIzq) >= alturaNodo(this.nodoIzq.nodoDch))) {
            return 1;
        } else if ((balance < -1) && (alturaNodo(this.nodoDch.nodoDch) >= alturaNodo(this.nodoDch.nodoIzq))) {
            return 2;
        } else if ((balance > 1) && (alturaNodo(this.nodoIzq.nodoIzq) < alturaNodo(this.nodoIzq.nodoDch))){
            return 3;
        } else if ((balance < -1) && (alturaNodo(this.nodoDch.nodoDch) < alturaNodo(this.nodoDch.nodoIzq))){
            return 4;
        }
        return 0;
    }

    private void equilibrar(NodoAVL<TipoDelDato> nodo) {
        if (nodo.reconocerDesequilibrio() == 1) {
            this.rotacionII(nodo);
        } else if (nodo.reconocerDesequilibrio() == 3) {
            this.rotacionID(nodo);
        } else if (nodo.reconocerDesequilibrio() == 2) {
            this.rotacionDD(nodo);
        } else if (nodo.reconocerDesequilibrio() == 4) {
            this.rotacionDI(nodo);
        }
    }

    private void rotacionDD(NodoAVL<TipoDelDato> nodo) {
        NodoAVL<TipoDelDato> temporal = new NodoAVL<>();
        temporal.copiaLigera(nodo);
        NodoAVL<TipoDelDato> nuevaRaiz = temporal.getNodoDch();
        temporal.setNodoDch(nuevaRaiz.getNodoIzq());
        nuevaRaiz.setNodoIzq(temporal);
        nodo.copiaLigera(nuevaRaiz);
    }

    private void rotacionII(NodoAVL<TipoDelDato> nodo) {
        NodoAVL<TipoDelDato> temporal = new NodoAVL<>();
        temporal.copiaLigera(nodo);
        NodoAVL<TipoDelDato> nuevaRaiz = temporal.getNodoIzq();
        temporal.setNodoIzq(nuevaRaiz.getNodoDch());
        nuevaRaiz.setNodoDch(temporal);
        nodo.copiaLigera(nuevaRaiz);
    }

    private void rotacionID(NodoAVL<TipoDelDato> nodo) {
        this.rotacionDD(nodo.getNodoIzq());
        this.rotacionII(nodo);
    }

    private void rotacionDI(NodoAVL<TipoDelDato> nodo) {
        this.rotacionII(nodo.getNodoDch());
        this.rotacionDD(nodo);
    }
    public TipoDelDato getDato() {
        return dato;
    }

    public void setDato(TipoDelDato dato) {
        this.dato = dato;
    }

    public NodoAVL<TipoDelDato> getNodoIzq() {
        return nodoIzq;
    }

    public void setNodoIzq(NodoAVL<TipoDelDato> nodo) {
        this.nodoIzq = nodo;
    }

    public NodoAVL<TipoDelDato> getNodoDch() {
        return nodoDch;
    }

    public void setNodoDch(NodoAVL<TipoDelDato> nodoDch) {
        this.nodoDch = nodoDch;
    }
}
