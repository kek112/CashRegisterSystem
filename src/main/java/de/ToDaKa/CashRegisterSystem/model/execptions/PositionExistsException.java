package de.ToDaKa.CashRegisterSystem.model.execptions;

public class PositionExistsException extends Exception
{
    public PositionExistsException(String message )
    {
        super( message );
    }
}