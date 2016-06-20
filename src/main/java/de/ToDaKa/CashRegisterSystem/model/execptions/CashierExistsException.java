package de.ToDaKa.CashRegisterSystem.model.execptions;

public class CashierExistsException extends Exception
{
    public CashierExistsException(String message )
    {
        super( message );
    }
}
