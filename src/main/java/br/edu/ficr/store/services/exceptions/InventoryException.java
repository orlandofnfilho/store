package br.edu.ficr.store.services.exceptions;

public class InventoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InventoryException(String msg) {
        super(msg);
    }

}