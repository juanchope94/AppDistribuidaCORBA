package sop_corba;


/**
* sop_corba/ClsPagoDTOHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* martes 26 de marzo de 2019 04:20:22 AM COT
*/

abstract public class ClsPagoDTOHelper
{
  private static String  _id = "IDL:sop_corba/ClsPagoDTO:1.0";

  public static void insert (org.omg.CORBA.Any a, sop_corba.ClsPagoDTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sop_corba.ClsPagoDTO extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "placa",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "noTicket",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (sop_corba.ClsPagoDTOHelper.id (), "ClsPagoDTO", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sop_corba.ClsPagoDTO read (org.omg.CORBA.portable.InputStream istream)
  {
    sop_corba.ClsPagoDTO value = new sop_corba.ClsPagoDTO ();
    value.id = istream.read_long ();
    value.placa = istream.read_string ();
    value.noTicket = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sop_corba.ClsPagoDTO value)
  {
    ostream.write_long (value.id);
    ostream.write_string (value.placa);
    ostream.write_string (value.noTicket);
  }

}
