package de.ToDaKa.CashRegisterSystem.model.execptions;


public class CustomerExistsException extends Exception
{
    public CustomerExistsException(String message )
    {
        super( message );
    }
}