package de.ToDaKa.CashRegisterSystem.model.execptions;

public class BonExistsException extends Exception
{
    public BonExistsException(String message )
    {
        super( message );
    }
}
