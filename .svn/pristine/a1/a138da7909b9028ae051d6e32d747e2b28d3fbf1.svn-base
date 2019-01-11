<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<table width='95%' border='0' align='center' cellpadding='0'
	cellspacing='0'>
	<tr>
		<td><table width='100%' height='21' border='0' cellpadding='0'
				cellspacing='0' require ='true'>
				<tr>
					
						<td align='left' valign='bottom' style="word-break: keep-all"><TABLE
								class='tableTitle'>
								<TR>
									<TD>教育经历</TD>
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
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>学校名称</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>开始时间</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>结束时间</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>所学专业</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>学位</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>培养方式</td>
				</tr>
				
				<%-- <c:forEach items="${requestScope.educateList}" var="educate"
					varStatus="obj">
					<c:if test="${obj.count%2 == '0'}">
						<TR class=listColorA>
					</c:if>
					<c:if test="${obj.count%2 != '0'}">
						<TR class=listColorB>
					</c:if>
					<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="1" class='check' /></td>
						<td height='0' style='word-break: break-all;'><input disable="true"
							name='userschoolname' maxlength='50' id='userschoolname_row1'
							value='' required='false' label='学校名称' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea disable="true"
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='userschoolstartdate'
								maxlength='50' id='userschoolstartdate_row1'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='结束时间'
								rows=1 cols=20 mask='date' name='userschoolenddate'
								maxlength='50' id='userschoolenddate_row1'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolmajor' maxlength='50' id='userschoolmajor_row1'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooldegree' maxlength='50' id='userschoolmajor_row1'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooltype' maxlength='50' id='userschoolmajor_row1'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</TR>
				</c:forEach> --%>
				
				
					<tr class='listColorA'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="1" class='check' /></td>
						<td height='0' style='word-break: break-all;'><input disable="false"
							name='userschoolname' maxlength='50' id='userschoolname_row1'
							value='' required='true' label='学校名称' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea disable="true"
								style='width: 100%' class='mask' required='true' label='开始时间'
								rows=1 cols=20 mask='date' name='userschoolstartdate'
								maxlength='50' id='userschoolstartdate_row1'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='true' label='结束时间'
								rows=1 cols=20 mask='date' name='userschoolenddate'
								maxlength='50' id='userschoolenddate_row1'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolmajor' maxlength='50' id='userschoolmajor_row1'
							value='' required='true' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooldegree' maxlength='50' id='userschooldegree_row1'
							value='' required='true' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooltype' maxlength='50' id='userschooltype_row1'
							value='' required='true' label='培养方式' type='text' class='text' />
						</td>
					</tr>
					<tr class='listColorB'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="2" class='check' /></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolname' maxlength='50' id='userschoolname_row2'
							value='' required='false' label='学校名称' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='userschoolstartdate'
								maxlength='50' id='userschoolstartdate_row2'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='结束时间'
								rows=1 cols=20 mask='date' name='userschoolenddate'
								maxlength='50' id='userschoolenddate_row2'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolmajor' maxlength='50' id='userschoolmajor_row2'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooldegree' maxlength='50' id='userschooldegree_row2'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooltype' maxlength='50' id='userschooltype_row2'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</tr>
					<tr class='listColorA'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="3" class='check' /></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolname' maxlength='50' id='userschoolname_row3'
							value='' required='false' label='学校名称' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='userschoolstartdate'
								maxlength='50' id='userschoolstartdate_row3'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='结束时间'
								rows=1 cols=20 mask='date' name='userschoolenddate'
								maxlength='50' id='userschoolenddate_row3'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolmajor' maxlength='50' id='userschoolmajor_row3'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooldegree' maxlength='50' id='userschooldegree_row3'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooltype' maxlength='50' id='userschooltype_row3'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</tr>
					<tr class='listColorB'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="4" class='check' /></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolname' maxlength='50' id='userschoolname_row4'
							value='' required='false' label='学校名称' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='userschoolstartdate'
								maxlength='50' id='userschoolstartdate_row4'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='结束时间'
								rows=1 cols=20 mask='date' name='userschoolenddate'
								maxlength='50' id='userschoolenddate_row4'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolmajor' maxlength='50' id='userschoolmajor_row4'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooldegree' maxlength='50' id='userschooldegree_row4'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooltype' maxlength='50' id='userschooltype_row4'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</tr>
					<tr class='listColorA'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="5" class='check' /></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolname' maxlength='50' id='userschoolname_row5'
							value='' required='false' label='学校名称' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='userschoolstartdate'
								maxlength='50' id='userschoolstartdate_row5'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='结束时间'
								rows=1 cols=20 mask='date' name='userschoolenddate'
								maxlength='50' id='userschoolenddate_row5'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='userschoolmajor' maxlength='50' id='userschoolmajor_row5'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooldegree' maxlength='50' id='userschooldegree_row5'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='userschooltype' maxlength='50' id='userschooltype_row5'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</tr>
			</table>
		</td>
	</tr>
</table>


