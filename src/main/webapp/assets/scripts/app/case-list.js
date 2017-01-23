    var caseListTable;

	//Plug-in to fetch page data 
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
	{
		return {
			"iStart":         oSettings._iDisplayStart,
			"iEnd":           oSettings.fnDisplayEnd(),
			"iLength":        oSettings._iDisplayLength,
			"iTotal":         oSettings.fnRecordsTotal(),
			"iFilteredTotal": oSettings.fnRecordsDisplay(),
			"iPage":          oSettings._iDisplayLength === -1 ?
				0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
			"iTotalPages":    oSettings._iDisplayLength === -1 ?
				0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
		};
	};
	
	var options = {
	    weekday: "short", year: "numeric", month: "short",
	    day: "numeric", hour: "2-digit", minute: "2-digit"
	};
 
	$(document).ready(function() {
	 
		caseListTable = $("#case-list").dataTable( {
	        "bProcessing": true,
	        "bServerSide": true,
	        "sort": "position",
	        //bStateSave variable you can use to save state on client cookies: set value "true" 
	        "bStateSave": false,
	        //Default: Page display length
	        "iDisplayLength": 10,
	        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
	        "iDisplayStart": 0,
	        "sPaginationType": "full_numbers",
	        "fnDrawCallback": function () {
	            //Get page numer on client. Please note: number start from 0 So
	            //for the first page you will see 0 second page 1 third page 2...
	            //Un-comment below alert to see page number
	        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
	        },         
	        "sAjaxSource": base_url + 'firm/case/list',
	        "aoColumns": [
				{ "mData": "caseDocketId" },
	            { "mData": "caseName" },
	            { "mData": "caseType.caseTypeDescription" },
	            { "mData": "caseStatus.status" },
	            { "mData": "client.firstName", "mRender": function (data, type, full) {
		          		if(data) {
		          			return full.client.lastName + ', ' + full.client.firstName;
		          		}
	          		} 
	            },
	            { "mData": "caseManager.firstName", "mRender": function (data, type, full) {
		          		if(data) {
		          			return full.caseManager.lastName + ', ' + full.caseManager.firstName;
		          		}
	          		}
	            },
	            { "mData": "dateTimeCreated",
	            	"mRender": function (data, type, full) {
		          		if(data) {
		          			var formmatedvalue = new Date(data).toLocaleTimeString("en-us", options);
		          			return formmatedvalue;
		          		}
		          	}
	            },
	            { "mData": null,
	            	"bSortable": false,
	            	"mRender": function(data, type, full) {
	            		var action = base_url + 'updateCase/' + full.caseDocketId;
	                    return '<form method="post" action="'+ action +'"><button type="submit" class="btn btn-info btn-sm">Details</button></form>';
	                }
	            }
	        ],
	        "aoColumnDefs": [ {
	          	"aTargets": [ 2 ],
	          	"mRender": function (data, type, full) {
	          		if(data) {
	          			return data;
	          		}
	          	},
	          	"aTargets": [ 3 ],
	          	"mRender": function (data, type, full) {
	          		if(data) {
	          			return data;
	          		}
	          	},
	          	"aTargets": [ 4 ],
	          	"mRender": function (data, type, full) {
	          		if(data) {
	          			return data;
	          		}
	          	},
	          	"aTargets": [ 5 ],
	          	"mRender": function (data, type, full) {
	          		if(data) {
	          			return data;
	          		}
	          	}
	         }
	        ]
	    });
		
		
		$('#active-client-list tbody').on( 'click', 'tr', function () {
			$(this).toggleClass('selected');
	    } );
		
		$('#active-client-list tbody').on( 'click', 'button', function () {
	    	var data = caseListTable.row( $(this).parents('tr') ).data();
	        //data.id;
	        
	    });
		
	});
 