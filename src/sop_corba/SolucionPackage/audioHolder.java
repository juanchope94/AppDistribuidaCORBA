package sop_corba.SolucionPackage;


/**
* sop_corba/SolucionPackage/audioHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* s�bado 23 de marzo de 2019 07:02:52 PM COT
*/

public final class audioHolder implements org.omg.CORBA.portable.Streamable
{
  public byte value[] = null;

  public audioHolder ()
  {
  }

  public audioHolder (byte[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.SolucionPackage.audioHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.SolucionPackage.audioHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.SolucionPackage.audioHelper.type ();
  }

}