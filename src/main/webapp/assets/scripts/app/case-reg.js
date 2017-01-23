		var activeClientsTable;
		var caseManagersTable;
		var documentsTable;
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
			
			$('#addFile').click(function() {
				$('#fileUploader').append('<input class="margin-top-10" type="file" name="file1" />');
			});
			
			//add more file components if Add is clicked
			$('#addNote').click(function() {
				$('#notesDiv').append('<textarea class="form-control" name="notes" placeholder="Case Notes" rows="3" cols="60"></textarea>');
			});
			
			
			/// Select Client Code Begin
			
			activeClientsTable = $("#active-client-list").DataTable( {
		        "bProcessing": true,
		        "bServerSide": true,
		        "sort": "position",
		        //bStateSave variable you can use to save state on client cookies: set value "true" 
		        "bStateSave": false,
		        //Default: Page display length
		        "iDisplayLength": 5,
		        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
		        "iDisplayStart": 0,
		        "fnDrawCallback": function () {
		            //Get page numer on client. Please note: number start from 0 So
		            //for the first page you will see 0 second page 1 third page 2...
		            //Un-comment below alert to see page number
		        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
		        },         
		        "sAjaxSource": base_url + 'client/list',
		        "aoColumns": [
					{ "mData": "id" },
		            { "mData": "firstName" },
		            { "mData": "lastName" },
		            { "mData": "phone1" },
		            { "mData": null,
		            	"bSortable": false,
		            	"mRender": function(data, type, full) {
		                    return '<button class="btn btn-info btn-sm">Select</button>';
		                }
		            }
		        ]
		    });
			
			$('#active-client-list tbody').on( 'click', 'tr', function () {
				$(this).toggleClass('selected');
		    } );
			
			$('#active-client-list tbody').on( 'click', 'button', function () {
		    	var data = activeClientsTable.row( $(this).parents('tr') ).data();
		        $("input[name='client.clientId']").val(data.id);
		        $("input[name='client.firstName']").val(data.firstName);
		        $("input[name='client.lastName']").val(data.lastName);
		        $('#selectClientModal').modal('toggle'); 
		    });
			
			/// Select Client Code End
			
			/// Select Case Manager Code Begin
			
			caseManagersTable = $("#active-case-manager-list").DataTable( {
		        "bProcessing": true,
		        "bServerSide": true,
		        "sort": "position",
		        //bStateSave variable you can use to save state on client cookies: set value "true" 
		        "bStateSave": false,
		        //Default: Page display length
		        "iDisplayLength": 5,
		        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
		        "iDisplayStart": 0,
		        "fnDrawCallback": function () {
		            //Get page numer on client. Please note: number start from 0 So
		            //for the first page you will see 0 second page 1 third page 2...
		            //Un-comment below alert to see page number
		        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
		        },         
		        "sAjaxSource": base_url + 'firmusers/list',
		        "aoColumns": [
					{ "mData": "userId" },
		            { "mData": "firstName" },
		            { "mData": "lastName" },
		            { "mData": null,
		            	"bSortable": false,
		            	"mRender": function(data, type, full) {
		                    return '<button class="btn btn-info btn-sm">Select</button>';
		                }
		            }
		        ]
		    });
			
			$('#active-case-manager-list tbody').on( 'click', 'tr', function () {
				$(this).toggleClass('selected');
		    } );
			
			$('#active-case-manager-list tbody').on( 'click', 'button', function () {
		    	var data = caseManagersTable.row( $(this).parents('tr') ).data();
		        $("input[name='caseManager.userId']").val(data.id);
		        $("input[name='caseManager.firstName']").val(data.firstName);
		        $("input[name='caseManager.lastName']").val(data.lastName);
		        $('#selectCaseManagerModal').modal('toggle'); 
		    });
			/// Select Case Manager Code End
			
			
			/// Documents Code Begin
			
			documentsTable = $("#document-list").DataTable( {
		        "bProcessing": false,
		        "bServerSide": false,
		        "sort": "position",
		        "bFilter": false,
		        "bLengthChange": false,
		        //bStateSave variable you can use to save state on client cookies: set value "true" 
		        "bStateSave": false,
		        //Default: Page display length
		        "iDisplayLength": 5,
		        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
		        "iDisplayStart": 0,
		        "fnDrawCallback": function () {
		            //Get page numer on client. Please note: number start from 0 So
		            //for the first page you will see 0 second page 1 third page 2...
		            //Un-comment below alert to see page number
		        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
		        },         
		        //"sAjaxSource": base_url + 'case/documents/list',
		        /*"aoColumns": [
					{ "mData": "caseDocumentId" },
		            { "mData": "documentName" },
		            { "mData": "documentType" },
		            { "mData": "dateTimeCreated" },
		            { "mData": null,
		            	"bSortable": false,
		            	"mRender": function(data, type, full) {
		                    return '<button class="btn btn-info btn-sm">View</button>';
		                }
		            }
		        ]*/
		    });
			
			$('#document-list tbody').on( 'click', 'tr', function () {
				$(this).toggleClass('selected');
		    } );
			
			$('#document-list tbody').on( 'click', 'button', function () {
		    	var data = documentsTable.row( $(this).parents('tr') ).data();
		        //data.id
		        $('#selectCaseManagerModal').modal('toggle'); 
		    });
			/// Documents Table Code End
		});