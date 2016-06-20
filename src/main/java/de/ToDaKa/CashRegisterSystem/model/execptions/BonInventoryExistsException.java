package de.ToDaKa.CashRegisterSystem.model.execptions;

public class BonInventoryExistsException extends Exception
{
    public BonInventoryExistsException(String message )
    {
        super( message );
    }
}
