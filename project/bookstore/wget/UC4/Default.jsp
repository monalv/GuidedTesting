            
<html>
<head>
<title>Book Store</title>
<meta name="GENERATOR" content="YesSoftware CodeCharge v.1.2.0 / JSP.ccp build 05/21/2001"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body style="background-color: #FFFFFF; color: #000000; font-family: Arial, Tahoma, Verdana, Helveticabackground-color: #FFFFFF; color: #000000; font-family: Arial, Tahoma, Verdana, Helvetica">
<center>
 <table>
  <tr>
   <td valign="top">
    <table style="">
<tr></tr>     <tr>
      <td style="background-color: #FFFFFF; border-width: 1"></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Default.jsp"><font style="font-size: 10pt; color: #000000"><img src="images/Logo_bookstore.gif" border="0"></font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Default.jsp"><font style="font-size: 10pt; color: #000000"><img src="images/icon_home.gif" border="0"></font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Registration.jsp"><font style="font-size: 10pt; color: #000000"><img src="images/icon_reg.gif" border="0"></font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="ShoppingCart.jsp"><font style="font-size: 10pt; color: #000000"><img src="images/icon_shop.gif" border="0"></font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Login.jsp"><font style="font-size: 10pt; color: #000000"><img src="images/icon_sign.gif" border="0"></font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="AdminMenu.jsp"><font style="font-size: 10pt; color: #000000"><img src="images/icon_admin.gif" border="0"></font></a></td>
     </tr>
    </table>
   
   </td>
  </tr>
 </table>
</center>
<hr>

 <table>
  <tr>
   <td valign="top">
    <table style="">
     <tr>
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="5"><a name="Search"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">Search</font></a></td>
     </tr>
     <form method="get" action="" name="Search">
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Category</font></td>
      <td style="background-color: #FFFFFF; border-width: 1"><select name="category_id"><option value="">All</option><option value="2" >Databases</option><option value="3" >HTML &amp; Web design</option><option value="1" >Programming</option></select></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Name of Book</font></td>
      <td style="background-color: #FFFFFF; border-width: 1"><input type="text"  name="name" maxlength="10" value="" size="10"></td>
     </tr>
     <tr>
      <td align="right" colspan="3"><input type="submit" value="Search"/></td>
     </tr>
     </form>
    </table>


    
    <table style="">
     <tr>
      <td colspan="1"  style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">More Search Options</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="AdvSearch.jsp"><font style="font-size: 10pt; color: #000000">Advanced search</font></a></td>
     </tr>
    </table>

    
    <table style="">
     <tr>
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="1"><a name="Categories"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">Categories</font></a></td>
     </tr>
     <tr>
      <td colspan="1" style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Books.jsp?category_id=1&"><font style="font-size: 10pt; color: #000000">Programming</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Books.jsp?category_id=2&"><font style="font-size: 10pt; color: #000000">Databases</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Books.jsp?category_id=3&"><font style="font-size: 10pt; color: #000000">HTML &amp; Web design</font></a></td>
     </tr>
    </table>

    
    <table style="">
     <tr>
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="1"><a name="Specials"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">Weekly Specials</font></a></td>
     </tr>

     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000"><b><font color="brown">Free Shipping on orders over $40</font></b>&nbsp;</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">For limited time only, until next Sunday, you can enjoy free shipping. Simply order more then $40 worth of books and shipping's on us.&nbsp;</font></td>
     </tr>
     <tr>
      <td colspan="2" style="background-color: #FFFFFF; border-width: 1">&nbsp;</td>
     </tr>
    </table>

    </td>
   <td valign="top"><table width="250"><tr><td></td></tr></table>
    <table style="">
     <tr>
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="1"><a name="Recommended"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">Recommended Titles</font></a></td>
     </tr>

     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=1&"><font style="font-size: 10pt; color: #000000">Web Database Development : Step by Step</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">Jim Buyens&nbsp;</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=1&"><font style="font-size: 10pt; color: #000000"><img border=0 src=images/books/0735609667.jpg></font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold">Price</font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">39.99&nbsp;</font></td>
     </tr>
     <tr>
      <td colspan="2" style="background-color: #FFFFFF; border-width: 1">&nbsp;</td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=6&"><font style="font-size: 10pt; color: #000000">MySQL &amp; PHP From Scratch</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">Wade Maxfield&nbsp;</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=6&"><font style="font-size: 10pt; color: #000000"><img border=0 src=images/books/0789724405.jpg></font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold">Price</font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">23.99&nbsp;</font></td>
     </tr>
     <tr>
      <td colspan="2" style="background-color: #FFFFFF; border-width: 1">&nbsp;</td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=7&"><font style="font-size: 10pt; color: #000000">MySQL and mSQL</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">Randy Jay Yarger, George Reese, Tim King&nbsp;</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=7&"><font style="font-size: 10pt; color: #000000"><img border=0 src=images/books/1565924347.jpg></font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold">Price</font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">27.96&nbsp;</font></td>
     </tr>
     <tr>
      <td colspan="2" style="background-color: #FFFFFF; border-width: 1">&nbsp;</td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=10&"><font style="font-size: 10pt; color: #000000">Beginning ASP Databases</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">John Kaufman, Thearon Willis, David Buser, Kevin Spencer, kauffman, John Kauffman&nbsp;</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=10&"><font style="font-size: 10pt; color: #000000"><img border=0 src=images/books/1861002726.jpg></font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold">Price</font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">39.99&nbsp;</font></td>
     </tr>
     <tr>
      <td colspan="2" style="background-color: #FFFFFF; border-width: 1">&nbsp;</td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=12&"><font style="font-size: 10pt; color: #000000">Black Belt Web Programming Methods; Servers, Security, Databases and Sites</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">&nbsp;</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=12&"><font style="font-size: 10pt; color: #000000"><img border=0 src=images/books/0879304979.gif></font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold">Price</font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">27.96&nbsp;</font></td>
     </tr>
     <tr>
      <td colspan="2" style="background-color: #FFFFFF; border-width: 1">&nbsp;</td>
     </tr>
     <tr>
      <td colspan="1" style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold">
       <a href_="#"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold">Previous</font></a>
       [ 1 ]
       <a href="Default.jsp?FormRecommended_Page=2#Form"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold">Next</font></a><br></td>
     </tr>
    </table>

    </td>
   <td valign="top">
    <table style="">
     <tr>
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="1"><a name="What"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">What We're Reading</font></a></td>
     </tr>

     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=22&"><font style="font-size: 10pt; color: #000000">A Sharp Combination</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">To get inside C#, Microsoft's new OO programming language, use A Preview of C# as a guide. It offers a preview of Visual Studio.NET and an overview of the .NET framework, and demonstrates how C# is integrated with ASP+, ADO+, and COM+ in .NET applications. You'll get examples of C# in action, too.&nbsp;</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=22&"><font style="font-size: 10pt; color: #000000"><img border=0 src=images/books/1861004877.jpg></font></a></td>
     </tr>
     <tr>
      <td colspan="2" style="background-color: #FFFFFF; border-width: 1">&nbsp;</td>
     </tr>
    </table>

    
    <table style="">
     <tr>
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="3"><a name="New"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">New & Notable</font></a></td>
     </tr>
     <tr>
      <td colspan="1" style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font></td>
      <td colspan="1" style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font></td>
      <td colspan="1" style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=21&"><font style="font-size: 10pt; color: #000000">1001 Web Site Construction Tips and Tricks</font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=21&"><font style="font-size: 10pt; color: #000000"><img border=0 src=images/books/1884133193.gif></font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">39.95&nbsp;</font></td>
     </tr>
    </table>

    
    <table style="">
     <tr>
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="1"><a name="Weekly"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">This Week's Featured Books</font></a></td>
     </tr>

     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=8&"><font style="font-size: 10pt; color: #000000">Flash 4 Magic</font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><a href="BookDetail.jsp?item_id=8&"><font style="font-size: 10pt; color: #000000"><img border=0 src=images/books/0735709491.jpg></font></a></td>
     </tr>
     <tr>
      <td style="background-color: #FFFFFF; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #CE7E00; font-weight: bold"></font> </td></tr><tr><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">If you're right in the middle of learning (or just jumping into) Flash to create and manipulate animations, music tracks, sound effects, and interface design, try the Flash 4 Magic. Inside are tutorials, graphic presentations, and a CD.&nbsp;</font></td>
     </tr>
     <tr>
      <td colspan="2" style="background-color: #FFFFFF; border-width: 1">&nbsp;</td>
     </tr>
    </table>

    
   </td>
  </tr>
 </table>

<center>
<hr size=1 width=60%>
 <table>
  <tr>
   <td valign="top">
    <table style="">
     <tr>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Default.jsp"><font style="font-size: 10pt; color: #000000">Home</font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Registration.jsp"><font style="font-size: 10pt; color: #000000">Registration</font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="ShoppingCart.jsp"><font style="font-size: 10pt; color: #000000">Shopping Cart</font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="Login.jsp"><font style="font-size: 10pt; color: #000000">Sign In</font></a></td>
      <td style="background-color: #FFFFFF; border-width: 1"><a href="AdminMenu.jsp"><font style="font-size: 10pt; color: #000000">Administration</font></a></td>
     </tr>
    </table>
    </td>
   
  </tr>
 </table>
 

<center><font face="Arial"><small>This dynamic site was generated with <a href="http://www.codecharge.com">CodeCharge</a></small></font></center>
</body>
</html>


