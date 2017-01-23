//Plug-in to fetch page data 
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ? 0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ? 0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};


$(document).ready(function() {
	

	$('#addDependent').on( 'click', function () {
		//$("#selectClientMemberModal").on('shown.bs.modal', function () {
		    //$(this).find("input,textarea,select").val('').end();
		//});
		//$("#selectClientMemberModal").on('hidden.bs.modal', function () {
			// Do something on hide event
		//});
    	// Call server data to show modal dialog here
        //$('#selectClientMemberModal').modal('toggle');
		
		var addMemberUrl = base_url + 'addMember';
    	var form = $('#client-registration-form');
    	form.attr({"method": "POST"});
    	form.attr({"action": addMemberUrl});
    	form.submit();
		
    });
	
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		var target = $(e.target).attr("href") // activated tab
		//alert(target);
		$("#selected-tab").val(target);
	});
	
	$("#dependent-list").dataTable();
});

function viewMember(updateMemberUrl) {
	var form = $('<form/>');
	form.attr({"method": "POST"});
	form.attr({"action": updateMemberUrl});
	form.submit();
}