
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fn" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function chkclick(id,tillid) {
		//alert(id);
		//alert(tillid);
		//alert(document.getElementById('chk_01').checked);
		if ($("#"+id).is(":checked")) {
			//if($("#chk_"+tillid))
			$("#trtillid" + tillid).addClass("check_trcss");
		} else {
			$("#trtillid" + tillid).removeClass("check_trcss");
		}
	}
	var checkedids="${checkid}";
</script>
<table border="1" width="100%">
	<tr>
		<td align="left" valign="top">
			<table id="table1" class="table_a" border="1" width="100%">
				<tbody>
					<tr>
						<th width=15%>
							POS号
						</th>
						<th width=25%>
							金额
						</th>
						<th width=25%>
							清机标志
						</th>
						<th width=25% align="center">
							操作
						</th>
					</tr>
					<c:forEach var="rl" items="${poslist}" begin="0"
						end="${perposlistsize-1}" varStatus="status">
						<tr id="trtillid${rl.tillid}" align="center" bgcolor="#ffffff">
							<td>
								${ rl.tillid}
							</td>
							<td>
								${ rl.amount}
							</td>
							<td>
								<c:if test="${rl.clearFlag=='1'}">已清机</c:if>
							</td>
							<td>
								<input type="button" class="getposstatus"
									onclick="javascript:freshPos('${rl.tillid}','${ status.index + 1}','table1')"
									value="刷新">
								<input type="checkbox" name="chkFlag" id="table1_bchk_${rl.tillid}"
									value="table1_bchk_${rl.tillid}"
									onclick="chkclick(this.id,'${rl.tillid}')" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</td>
		<td align="left" valign="top">
			<table id="table2" class="table_a" border="1" width="100%">
				<tbody>
					<tr>
						<th width=15%>
							POS号
						</th>
						<th width=25%>
							金额
						</th>
						<th width=25%>
							清机标志
						</th>
						<th width=25% align="center">
							操作
						</th>
					</tr>
					<c:forEach var="rl" items="${poslist}" begin="${perposlistsize}"
						end="${perposlistsize*2-1}" varStatus="status">
						<tr id="trtillid${rl.tillid}" align="center" bgcolor="#ffffff">
							<td>
								${ rl.tillid}
							</td>
							<td>
								${ rl.amount}
							</td>
							<td>
								<c:if test="${rl.clearFlag=='1'}">已清机</c:if>
							</td>
							<td>
								<input type="button" class="getposstatus"
									onclick="javascript:freshPos('${rl.tillid}','${ status.index - perposlistsize + 1}','table2')"
									value="刷新">
									<input type="checkbox" name="chkFlag" id="table2_bchk_${rl.tillid}"
									value="table2_bchk_${rl.tillid}"
									onclick="chkclick(this.id,'${rl.tillid}')" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</td>
		<td align="left" valign="top">
			<table id="table3" class="table_a" border="1" width="100%">
				<tbody>
					<tr>
						<th width=15%>
							POS号
						</th>
						<th width=25%>
							金额
						</th>
						<th width=25%>
							清机标志
						</th>
						<th width=25% align="center">
							操作
						</th>
					</tr>
					<c:forEach var="rl" items="${poslist}" begin="${perposlistsize*2}"
						end="${perposlistsize*3-1}" varStatus="status">
						<tr id="trtillid${rl.tillid}" align="center" bgcolor="#ffffff">
							<td>
								${ rl.tillid}
							</td>
							<td>
								${ rl.amount}
							</td>
							<td>
								<c:if test="${rl.clearFlag=='1'}">已清机</c:if>
							</td>
							<td>
								<input type="button" class="getposstatus"
									onclick="javascript:freshPos('${rl.tillid}','${ status.index -perposlistsize*2 + 1}','table3')"
									value="刷新">
									<input type="checkbox" name="chkFlag" id="table3_bchk_${rl.tillid}"
									value="table3_bchk_${rl.tillid}"
									onclick="chkclick(this.id,'${rl.tillid}')" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</td>
		<td align="left" valign="top">
			<table id="table4" class="table_a" border="1" width="100%">
				<tbody>
					<tr>
						<th width=15%>
							POS号
						</th>
						<th width=25%>
							金额
						</th>
						<th width=25%>
							清机标志
						</th>
						<th width=25% align="center">
							操作
						</th>
					</tr>
					<c:forEach var="rl" items="${poslist}" begin="${perposlistsize*3}"
						varStatus="status">
						<tr id="trtillid${rl.tillid}" align="center" bgcolor="#ffffff">
							<td>
								${ rl.tillid}
							</td>
							<td>
								${ rl.amount}
							</td>
							<td>
								<c:if test="${rl.clearFlag=='1'}">已清机</c:if>
							</td>
							<td>
								<input type="button" class="getposstatus"
									onclick="javascript:freshPos('${rl.tillid}','${ status.index - perposlistsize*3 + 1}','table4')"
									value="刷新">
									<input type="checkbox" name="chkFlag" id="table4_bchk_${rl.tillid}"
									value="table4_bchk_${rl.tillid}"
									onclick="chkclick(this.id,'${rl.tillid}')" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</td>
	</tr>
</table>