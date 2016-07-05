package de.ToDaKa.CashRegisterSystem.model.execptions;


public class InventoryExistsException extends Exception
{
    public InventoryExistsException(String message )
    {
        super( message );
    }
}