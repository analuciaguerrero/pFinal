package com.example.demojavafx.estructurasDeDatos.ArbolAVL;

import com.example.demojavafx.excepciones.DuplicateElement;
import com.example.demojavafx.excepciones.NonexistentElement;

public class Nodo<TipoDelDato>{
    private TipoDelDato dato;
    private Nodo<TipoDelDato> nodoIzq;
    private Nodo<TipoDelDato> nodoDch;
    public Nodo() {
        this.dato = null;
    }

    public Nodo(TipoDelDato dato) {
        this.dato = dato;
    }

    private Integer compararDatos(TipoDelDato a, TipoDelDato b) {
        Comparable dato1 = (Comparable) a;
        Comparable dato2 = (Comparable) b;
        return dato1.compareTo(dato2);
    }

    protected int alturaNodo(Nodo<TipoDelDato> nodo) {
        return calcularAlturaRecursivo(nodo);
    }

    private int calcularAlturaRecursivo(Nodo<TipoDelDato> nodo) {
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

    protected void del(Nodo<TipoDelDato> padre, Nodo<TipoDelDato> actual, TipoDelDato a) throws NonexistentElement {
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
                Nodo<TipoDelDato> temporal = new Nodo<>();
                if (actual.getNodoIzq() != null) {
                    temporal.copiaLigera(actual.getNodoIzq());
                } else {
                    temporal.copiaLigera(actual.getNodoDch());
                }
                actual.copiaLigera(temporal);
            } else {
                Nodo<TipoDelDato> nodoDespués = actual.getNodoDch();
                Nodo<TipoDelDato> nodoAntDespués = actual;
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

    protected void add(Nodo<TipoDelDato> nodo, TipoDelDato dato) throws DuplicateElement {
        Integer comparacion = this.compararDatos(dato, nodo.getDato());
        if (comparacion == 0) {
            throw (new DuplicateElement("Este elemento ya está en el arbol"));
        } else if (comparacion == -1) {
            if (nodo.getNodoIzq() != null) {
                add(nodo.getNodoIzq(), dato);
                this.equilibrar(nodo);
            } else {
                nodo.setNodoIzq(new Nodo<>(dato));
            }
        } else {
            if (nodo.getNodoDch() != null) {
                add(nodo.getNodoDch(), dato);
                this.equilibrar(nodo);
            } else {
                nodo.setNodoDch(new Nodo<>(dato));
            }
        }
    }

    private void copiaLigera(Nodo<TipoDelDato> nodoCopiado) {
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

    private void equilibrar(Nodo<TipoDelDato> nodo) {
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

    private void rotacionDD(Nodo<TipoDelDato> nodo) {
        Nodo<TipoDelDato> temporal = new Nodo<>();
        temporal.copiaLigera(nodo);
        Nodo<TipoDelDato> nuevaRaiz = temporal.getNodoDch();
        temporal.setNodoDch(nuevaRaiz.getNodoIzq());
        nuevaRaiz.setNodoIzq(temporal);
        nodo.copiaLigera(nuevaRaiz);
    }

    private void rotacionII(Nodo<TipoDelDato> nodo) {
        Nodo<TipoDelDato> temporal = new Nodo<>();
        temporal.copiaLigera(nodo);
        Nodo<TipoDelDato> nuevaRaiz = temporal.getNodoIzq();
        temporal.setNodoIzq(nuevaRaiz.getNodoDch());
        nuevaRaiz.setNodoDch(temporal);
        nodo.copiaLigera(nuevaRaiz);
    }

    private void rotacionID(Nodo<TipoDelDato> nodo) {
        this.rotacionDD(nodo.getNodoIzq());
        this.rotacionII(nodo);
    }

    private void rotacionDI(Nodo<TipoDelDato> nodo) {
        this.rotacionII(nodo.getNodoDch());
        this.rotacionDD(nodo);
    }
    public TipoDelDato getDato() {
        return dato;
    }

    public void setDato(TipoDelDato dato) {
        this.dato = dato;
    }

    public Nodo<TipoDelDato> getNodoIzq() {
        return nodoIzq;
    }

    public void setNodoIzq(Nodo<TipoDelDato> nodo) {
        this.nodoIzq = nodo;
    }

    public Nodo<TipoDelDato> getNodoDch() {
        return nodoDch;
    }

    public void setNodoDch(Nodo<TipoDelDato> nodoDch) {
        this.nodoDch = nodoDch;
    }
}
