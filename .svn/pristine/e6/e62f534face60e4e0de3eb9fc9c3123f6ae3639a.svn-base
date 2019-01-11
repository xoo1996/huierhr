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
									<TD>工作经历</TD>
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
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>开始时间</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>结束时间</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>工作单位</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>职位</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>薪资标准</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>离职原因</td>
					<td height='0' nowrap class='tableHead'><span style='color:red'>*</span>证明人/电话</td>
				</tr>
				
			<%-- 	<c:forEach items="${requestScope.workList}" var="work" varStatus="obj">
					<c:if test="${obj.count%2 == '0'}">
						<TR class=listColorA text-align=center>
					</c:if>
					<c:if test="${obj.count%2 != '0'}">
						<TR class=listColorB >
					</c:if>
					<TD  style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workstartdateStr }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workenddateStr }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workplace }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workposition }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.worksalary }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workleavereason }" /></TD>
					<TD style="text-align:center" style="WORD-BREAK: break-all"><c:out value="${work.workprove }" /></TD>
					</tr>
				</c:forEach> --%>
				
				
				
				
				
					<tr class='listColorA'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="1" class='check' /></td>
						<td height='0' style='word-break: break-all;'><textarea disable="true"
								style='width: 100%' class='mask' required='true' label='开始时间'
								rows=1 cols=20 mask='date' name='workstartdate'
								maxlength='50' id='workstartdate_row1'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea disable="true"
								style='width: 100%' class='mask' required='true' label='开始时间'
								rows=1 cols=20 mask='date' name='workenddate'
								maxlength='50' id='workenddate_row1'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='workplace' maxlength='50' id='workplace_row1'
							value='' required='true' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workposition' maxlength='50' id='workposition_row1'
							value='' required='true' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='worksalary' maxlength='50' id='worksalary_row1'
							value='' required='true' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workleavereason' maxlength='50' id='workleavereason_row1'
							value='' required='true' label='培养方式' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workprove' maxlength='50' id='workprove_row1'
							value='' required='true' label='培养方式' type='text' class='text' />
						</td>
					</tr>
					<tr class='listColorB'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="2" class='check' /></td>
						<td height='0' style='word-break: break-all;'><textarea disable="true"
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workstartdate'
								maxlength='50' id='workstartdate_row2'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workenddate'
								maxlength='50' id='workenddate_row2'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='workplace' maxlength='50' id='workplace_row2'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workposition' maxlength='50' id='workposition_row2'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='worksalary' maxlength='50' id='worksalary_row2'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workleavereason' maxlength='50' id='workleavereason_row2'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workprove' maxlength='50' id='workprove_row2'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</tr>
					<tr class='listColorA'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="3" class='check' /></td>
						<td height='0' style='word-break: break-all;'><textarea disable="true"
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workstartdate'
								maxlength='50' id='workstartdate_row3'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workenddate'
								maxlength='50' id='workenddate_row3'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='workplace' maxlength='50' id='workplace_row3'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workposition' maxlength='50' id='workposition_row3'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='worksalary' maxlength='50' id='worksalary_row3'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workleavereason' maxlength='50' id='workleavereason_row3'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workprove' maxlength='50' id='workprove_row3'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</tr>
					<tr class='listColorB'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="4" class='check' /></td>
						<td height='0' style='word-break: break-all;'><textarea disable="true"
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workstartdate'
								maxlength='50' id='workstartdate_row4'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workenddate'
								maxlength='50' id='workenddate_row4'></textarea></td>
						<td height='0' style='word-break: break-all;'><input
							name='workplace' maxlength='50' id='workplace_row4'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workposition' maxlength='50' id='workposition_row4'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='worksalary' maxlength='50' id='worksalary_row4'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workleavereason' maxlength='50' id='workleavereason_row4'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workprove' maxlength='50' id='workprove_row4'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</tr>
					<tr class='listColorA'>
						<td height="0" align="center" nowrap><input type="checkbox"
							name="chk" value="5" class='check' /></td>
						<td height='0' style='word-break: break-all;'><textarea disable="true"
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workstartdate'
								maxlength='50' id='workstartdate_row5'></textarea></td>
						<td height='0' style='word-break: break-all;'><textarea
								style='width: 100%' class='mask' required='false' label='开始时间'
								rows=1 cols=20 mask='date' name='workenddate'
								maxlength='50' id='workenddate_row5'></textarea></td>
						
						<td height='0' style='word-break: break-all;'><input
							name='workplace' maxlength='50' id='workplace_row5'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workposition' maxlength='50' id='workposition_row5'
							value='' required='false' label='所学专业' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='worksalary' maxlength='50' id='worksalary_row5'
							value='' required='false' label='学位' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workleavereason' maxlength='50' id='workleavereason_row5'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
						<td height='0' style='word-break: break-all;'><input
							name='workprove' maxlength='50' id='workprove_row5'
							value='' required='false' label='培养方式' type='text' class='text' />
						</td>
					</tr> 
			</table>
		</td>
	</tr>
</table>


