sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,name,age,userName,roleId,create_date

updateSample
===
	
	id=#id#,name=#name#,age=#age#,userName=#username#,roleId=#roleid#,create_date=#createDate#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(age)){
	 and age=#age#
	@}
	@if(!isEmpty(username)){
	 and userName=#username#
	@}
	@if(!isEmpty(roleid)){
	 and roleId=#roleid#
	@}
	@if(!isEmpty(createDate)){
	 and create_date=#createDate#
	@}
	
	