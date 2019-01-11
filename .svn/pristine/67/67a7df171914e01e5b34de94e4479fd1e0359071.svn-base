<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0'>
	<tr>
		<td><table width='100%' height='21' border='0' cellpadding='0'
				cellspacing='0'>
				<tr>
					
						<td align='left' valign='bottom' style="word-break: keep-all"><TABLE
								class='tableTitle'>
								<TR>
									<TD>家庭情况</TD>
								</TR>
							</TABLE></td>
						<td width='10' class=''>&nbsp;</td>
						<td>&nbsp;</td>
						<td width='40%'>&nbsp;</td>
				
				</tr>
			</table></td>
	</tr>
</table>
<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0' class='tableList'>
	<tr>
		<td class=''>
			<table id='resultset' width='100%' border='0' align='center'
				cellspacing='1'>
				<tr align='center'>
					<td width='3%' height='0' class='tableHead'><input
						type='checkbox' name='checkall' class='check'
						onclick="selectall(document.all('chk'))"></td>
					<td height='0' nowrap class='tableHead'>姓名</td>
					<td height='0' nowrap class='tableHead'>与本人关系</td>
					<td height='0' nowrap class='tableHead'>出身年月</td>
					<td height='0' nowrap class='tableHead'>工作或学习单位</td>
					<td height='0' nowrap class='tableHead'>联系方式</td>
				</tr>
			<%-- 	<c:forEach items="${requestScope.familyList}" var="family" varStatus="obj">
					<c:if test="${obj.count%2 == '0'}">
						<TR class=listColorA>
					</c:if>
					<c:if test="${obj.count%2 != '0'}">
						<TR class=listColorB>
					</c:if>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="1" class='check' /></td>
						<td height='0' style='word-break: break-all;'><input
							name='familyname' maxlength='50' id='familyname_row1'
							value='' required='false' label='姓名' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='familycall' maxlength='50' id='familycall_row2'
							value='' required='false' label='与本人关系' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='出身年月'
								rows=1 cols=20 mask='date' name='familybirthday'
								maxlength='50' id='familybirthday_row1'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='familyworkplace' maxlength='50' id='familyworkplace_row1'
							value='' required='false' label='工作或学习单位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='familyphoneno' maxlength='50' id='familyphoneno_row1'
							value='' required='false' label='联系方式' type='text' class='text' />
						</td>
					</TR>
				</c:forEach> --%>
				
				
				
				
				<tr class='listColorA'>
					<td height="0" align="center" nowrap><input type="checkbox"
						name="chk" value="1" class='check' /></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyname' maxlength='50' id='familyname_row1'
						value='' required='false' label='姓名' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familycall' maxlength='50' id='familycall_row2'
						value='' required='false' label='与本人关系' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><textarea
							style='width: 100%' class='mask' required='false' label='出身年月'
							rows=1 cols=20 mask='date' name='familybirthday'
							maxlength='50' id='familybirthday_row1'></textarea></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyworkplace' maxlength='50' id='familyworkplace_row1'
						value='' required='false' label='工作或学习单位' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familyphoneno' maxlength='50' id='familyphoneno_row1'
						value='' required='false' label='联系方式' type='text' class='text' />
					</td>
				</tr>
				<tr class='listColorB'>
					<td height="0" align="center" nowrap><input type="checkbox"
						name="chk" value="1" class='check' /></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyname' maxlength='50' id='familyname_row1'
						value='' required='false' label='姓名' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familycall' maxlength='50' id='familycall_row2'
						value='' required='false' label='与本人关系' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><textarea
							style='width: 100%' class='mask' required='false' label='出身年月'
							rows=1 cols=20 mask='date' name='familybirthday'
							maxlength='50' id='familybirthday_row1'></textarea></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyworkplace' maxlength='50' id='familyworkplace_row1'
						value='' required='false' label='工作或学习单位' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familyphoneno' maxlength='50' id='familyphoneno_row1'
						value='' required='false' label='联系方式' type='text' class='text' />
					</td>
				</tr>
				<tr class='listColorA'>
					<td height="0" align="center" nowrap><input type="checkbox"
						name="chk" value="1" class='check' /></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyname' maxlength='50' id='familyname_row1'
						value='' required='false' label='姓名' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familycall' maxlength='50' id='familycall_row2'
						value='' required='false' label='与本人关系' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><textarea
							style='width: 100%' class='mask' required='false' label='出身年月'
							rows=1 cols=20 mask='date' name='familybirthday'
							maxlength='50' id='familybirthday_row1'></textarea></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyworkplace' maxlength='50' id='familyworkplace_row1'
						value='' required='false' label='工作或学习单位' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familyphoneno' maxlength='50' id='familyphoneno_row1'
						value='' required='false' label='联系方式' type='text' class='text' />
					</td>
				</tr>
				<tr class='listColorB'>
					<td height="0" align="center" nowrap><input type="checkbox"
						name="chk" value="1" class='check' /></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyname' maxlength='50' id='familyname_row1'
						value='' required='false' label='姓名' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familycall' maxlength='50' id='familycall_row2'
						value='' required='false' label='与本人关系' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><textarea
							style='width: 100%' class='mask' required='false' label='出身年月'
							rows=1 cols=20 mask='date' name='familybirthday'
							maxlength='50' id='familybirthday_row1'></textarea></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyworkplace' maxlength='50' id='familyworkplace_row1'
						value='' required='false' label='工作或学习单位' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familyphoneno' maxlength='50' id='familyphoneno_row1'
						value='' required='false' label='联系方式' type='text' class='text' />
					</td>
				</tr>
				<tr class='listColorA'>
					<td height="0" align="center" nowrap><input type="checkbox"
						name="chk" value="1" class='check' /></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyname' maxlength='50' id='familyname_row1'
						value='' required='false' label='姓名' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familycall' maxlength='50' id='familycall_row2'
						value='' required='false' label='与本人关系' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><textarea
							style='width: 100%' class='mask' required='false' label='出身年月'
							rows=1 cols=20 mask='date' name='familybirthday'
							maxlength='50' id='familybirthday_row1'></textarea></td>
					<td height='0' style='word-break: break-all;'><input
						name='familyworkplace' maxlength='50' id='familyworkplace_row1'
						value='' required='false' label='工作或学习单位' type='text' class='text' />
					</td>
					<td height='0' style='word-break: break-all;'><input
						name='familyphoneno' maxlength='50' id='familyphoneno_row1'
						value='' required='false' label='联系方式' type='text' class='text' />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


