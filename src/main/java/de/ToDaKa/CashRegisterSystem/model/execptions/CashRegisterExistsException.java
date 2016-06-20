package de.ToDaKa.CashRegisterSystem.model.execptions;

public class CashRegisterExistsException extends Exception
{
    public CashRegisterExistsException(String message )
    {
        super( message );
    }
}
