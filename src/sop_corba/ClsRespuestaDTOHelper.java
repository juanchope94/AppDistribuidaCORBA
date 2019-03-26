package sop_corba;


/**
* sop_corba/ClsRespuestaDTOHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* martes 26 de marzo de 2019 01:34:52 AM COT
*/

abstract public class ClsRespuestaDTOHelper
{
  private static String  _id = "IDL:sop_corba/ClsRespuestaDTO:1.0";

  public static void insert (org.omg.CORBA.Any a, sop_corba.ClsRespuestaDTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sop_corba.ClsRespuestaDTO extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "opcion",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (sop_corba.audioHelper.id (), "audio", _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "mensajeAudio",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (sop_corba.ClsRespuestaDTOHelper.id (), "ClsRespuestaDTO", _members0);
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

  public static sop_corba.ClsRespuestaDTO read (org.omg.CORBA.portable.InputStream istream)
  {
    sop_corba.ClsRespuestaDTO value = new sop_corba.ClsRespuestaDTO ();
    value.opcion = istream.read_long ();
    value.mensajeAudio = sop_corba.audioHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sop_corba.ClsRespuestaDTO value)
  {
    ostream.write_long (value.opcion);
    sop_corba.audioHelper.write (ostream, value.mensajeAudio);
  }

}
