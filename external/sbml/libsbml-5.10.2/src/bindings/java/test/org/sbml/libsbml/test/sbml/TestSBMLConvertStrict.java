/*
 * @file    TestSBMLConvertStrict.java
 * @brief   SBMLConvert unit tests for strict conversion
 *
 * @author  Akiya Jouraku (Java conversion)
 * @author  Sarah Keating
 
 * 
 * ====== WARNING ===== WARNING ===== WARNING ===== WARNING ===== WARNING ======
 *
 * DO NOT EDIT THIS FILE.
 *
 * This file was generated automatically by converting the file located at
 * src/sbml/test/TestSBMLConvertStrict.c
 * using the conversion program dev/utilities/translateTests/translateTests.pl.
 * Any changes made here will be lost the next time the file is regenerated.
 *
 * -----------------------------------------------------------------------------
 * This file is part of libSBML.  Please visit http://sbml.org for more
 * information about SBML, and the latest version of libSBML.
 *
 * Copyright 2005-2010 California Institute of Technology.
 * Copyright 2002-2005 California Institute of Technology and
 *                     Japan Science and Technology Corporation.
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation.  A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as http://sbml.org/software/libsbml/license.html
 * -----------------------------------------------------------------------------
 */

package org.sbml.libsbml.test.sbml;

import org.sbml.libsbml.*;

import java.io.File;
import java.lang.AssertionError;

public class TestSBMLConvertStrict {

  static void assertTrue(boolean condition) throws AssertionError
  {
    if (condition == true)
    {
      return;
    }
    throw new AssertionError();
  }

  static void assertEquals(Object a, Object b) throws AssertionError
  {
    if ( (a == null) && (b == null) )
    {
      return;
    }
    else if ( (a == null) || (b == null) )
    {
      throw new AssertionError();
    }
    else if (a.equals(b))
    {
      return;
    }

    throw new AssertionError();
  }

  static void assertNotEquals(Object a, Object b) throws AssertionError
  {
    if ( (a == null) && (b == null) )
    {
      throw new AssertionError();
    }
    else if ( (a == null) || (b == null) )
    {
      return;
    }
    else if (a.equals(b))
    {
      throw new AssertionError();
    }
  }

  static void assertEquals(boolean a, boolean b) throws AssertionError
  {
    if ( a == b )
    {
      return;
    }
    throw new AssertionError();
  }

  static void assertNotEquals(boolean a, boolean b) throws AssertionError
  {
    if ( a != b )
    {
      return;
    }
    throw new AssertionError();
  }

  static void assertEquals(int a, int b) throws AssertionError
  {
    if ( a == b )
    {
      return;
    }
    throw new AssertionError();
  }

  static void assertNotEquals(int a, int b) throws AssertionError
  {
    if ( a != b )
    {
      return;
    }
    throw new AssertionError();
  }

  public void test_SBMLConvertStrict_convertL1ParamRule()
  {
    SBMLDocument d = new  SBMLDocument(1,2);
    Model m = d.createModel();
    Compartment c = m.createCompartment();
    c.setId( "c");
    Parameter p = m.createParameter();
    p.setId( "p");
    Parameter p1 = m.createParameter();
    p1.setId( "p1");
    ASTNode math = libsbml.parseFormula("p");
    Rule ar = m.createAssignmentRule();
    ar.setVariable( "p1");
    ar.setMath(math);
    ar.setUnits( "mole");
    assertTrue( d.setLevelAndVersion(2,1,true) == true );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 1 );
    Rule r1 = d.getModel().getRule(0);
    assertTrue( r1.getUnits().equals("") == true );
    d = null;
  }

  public void test_SBMLConvertStrict_convertNonStrictSBO()
  {
    SBMLDocument d = new  SBMLDocument(2,4);
    Model m = d.createModel();
    Compartment c = m.createCompartment();
    c.setId( "c");
    c.setConstant(false);
    (c).setSBOTerm(64);
    assertTrue( d.setLevelAndVersion(2,3,true) == false );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 4 );
    assertTrue( d.setLevelAndVersion(2,2,true) == false );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 4 );
    assertTrue( d.setLevelAndVersion(2,1,true) == true );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 1 );
    Compartment c1 = d.getModel().getCompartment(0);
    assertTrue( (c1).getSBOTerm() == -1 );
    assertTrue( d.setLevelAndVersion(1,2,true) == true );
    assertTrue( d.getLevel() == 1 );
    assertTrue( d.getVersion() == 2 );
    Compartment c2 = d.getModel().getCompartment(0);
    assertTrue( (c2).getSBOTerm() == -1 );
    d = null;
  }

  public void test_SBMLConvertStrict_convertNonStrictUnits()
  {
    SBMLDocument d = new  SBMLDocument(2,4);
    Model m = d.createModel();
    Compartment c = m.createCompartment();
    c.setId( "c");
    c.setConstant(false);
    Parameter p = m.createParameter();
    p.setId( "p");
    p.setUnits( "mole");
    ASTNode math = libsbml.parseFormula("p");
    Rule ar = m.createAssignmentRule();
    ar.setVariable( "c");
    ar.setMath(math);
    assertTrue( d.setLevelAndVersion(2,1,true) == false );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 4 );
    assertTrue( d.setLevelAndVersion(2,2,true) == false );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 4 );
    assertTrue( d.setLevelAndVersion(2,3,true) == false );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 4 );
    assertTrue( d.setLevelAndVersion(1,2,true) == false );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 4 );
    d = null;
  }

  public void test_SBMLConvertStrict_convertSBO()
  {
    SBMLDocument d = new  SBMLDocument(2,4);
    Model m = d.createModel();
    Compartment c = m.createCompartment();
    c.setId( "c");
    (c).setSBOTerm(240);
    assertTrue( d.setLevelAndVersion(2,3,true) == true );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 3 );
    assertTrue( d.setLevelAndVersion(2,2,true) == true );
    assertTrue( d.getLevel() == 2 );
    assertTrue( d.getVersion() == 2 );
    Compartment c1 = d.getModel().getCompartment(0);
    assertTrue( (c1).getSBOTerm() == -1 );
    d = null;
  }

  public void test_SBMLConvertStrict_convertToL1()
  {
    SBMLDocument d = new  SBMLDocument(2,4);
    Model m = d.createModel();
    (m).setMetaId( "_m");
    Compartment c = m.createCompartment();
    c.setId( "c");
    (c).setSBOTerm(240);
    Species s = m.createSpecies();
    s.setId( "s");
    s.setCompartment( "c");
    assertTrue( d.setLevelAndVersion(1,2,true) == true );
    assertTrue( d.getLevel() == 1 );
    assertTrue( d.getVersion() == 2 );
    Model m1 = d.getModel();
    assertTrue( (m1).getMetaId().equals("") == true );
    Compartment c1 = m1.getCompartment(0);
    assertTrue( (c1).getSBOTerm() == -1 );
    Species s1 = m1.getSpecies(0);
    assertTrue( s1.getHasOnlySubstanceUnits() == false );
    d = null;
  }

  /**
   * Loads the SWIG-generated libSBML Java module when this class is
   * loaded, or reports a sensible diagnostic message about why it failed.
   */
  static
  {
    String varname;
    String shlibname;

    if (System.getProperty("mrj.version") != null)
    {
      varname = "DYLD_LIBRARY_PATH";    // We're on a Mac.
      shlibname = "libsbmlj.jnilib and/or libsbml.dylib";
    }
    else
    {
      varname = "LD_LIBRARY_PATH";      // We're not on a Mac.
      shlibname = "libsbmlj.so and/or libsbml.so";
    }

    try
    {
      System.loadLibrary("sbmlj");
      // For extra safety, check that the jar file is in the classpath.
      Class.forName("org.sbml.libsbml.libsbml");
    }
    catch (SecurityException e)
    {
      e.printStackTrace();
      System.err.println("Could not load the libSBML library files due to a"+
                         " security exception.\n");
      System.exit(1);
    }
    catch (UnsatisfiedLinkError e)
    {
      e.printStackTrace();
      System.err.println("Error: could not link with the libSBML library files."+
                         " It is likely\nyour " + varname +
                         " environment variable does not include the directories\n"+
                         "containing the " + shlibname + " library files.\n");
      System.exit(1);
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
      System.err.println("Error: unable to load the file libsbmlj.jar."+
                         " It is likely\nyour -classpath option and CLASSPATH" +
                         " environment variable\n"+
                         "do not include the path to libsbmlj.jar.\n");
      System.exit(1);
    }
  }
}
