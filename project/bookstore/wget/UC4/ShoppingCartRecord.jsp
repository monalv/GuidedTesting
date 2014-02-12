            
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
      <td style="background-color: #336699; text-align: Center; border-style: outset; border-width: 1" colspan="2"><font style="font-size: 12pt; color: #FFFFFF; font-weight: bold">ShoppingCart</font></td>
     </tr>
     <form method="get" action="ShoppingCartRecord.jsp" name="ShoppingCartRecord">
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Item</font></td><td style="background-color: #FFFFFF; border-width: 1"><font style="font-size: 10pt; color: #000000">Web Database Development : Step by Step&nbsp;</font></td>
     </tr>
     <tr>
      <td style="background-color: #FFEAC5; border-style: inset; border-width: 0"><font style="font-size: 10pt; color: #000000">Quantity</font></td><td style="background-color: #FFFFFF; border-width: 1"><input type="text"  name="quantity" maxlength="5" value="1" size="5"></td>
     </tr>
     <tr>
      <td colspan="2" align="right"><input type="submit" value="Update" onclick="document.ShoppingCartRecord.FormAction.value = 'update';"><input type="submit" value="Delete" onclick="document.ShoppingCartRecord.FormAction.value = 'delete';"><input type="submit" value="Cancel" onclick="document.ShoppingCartRecord.FormAction.value = 'cancel';"><input type="hidden" name="FormName" value="ShoppingCartRecord"><input type="hidden" value="update" name="FormAction"><input type="hidden" name="order_id" value="1"><input type="hidden" name="member_id" value="2"><input type="hidden" name="PK_order_id" value="1"/></td>
     </tr>
     </form>
    </table>

    <SCRIPT Language="JavaScript">
if (document.forms["ShoppingCartRecord"])
document.ShoppingCartRecord.onsubmit=delconf;
function delconf() {
if (document.ShoppingCartRecord.FormAction.value == 'delete')
  return confirm('Delete record?');
}
</SCRIPT>
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


