 
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
	    weekday: "long", year: "numeric", month: "short",
	    day: "numeric", hour: "2-digit", minute: "2-digit"
	};
 
	$(document).ready(function() {
	 
		$("#client-list").dataTable( {
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
	        "sDom": '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',
	        "fnDrawCallback": function () {
	            //Get page numer on client. Please note: number start from 0 So
	            //for the first page you will see 0 second page 1 third page 2...
	            //Un-comment below alert to see page number
	        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
	        },         
	        "sAjaxSource": base_url + 'client/list',
	        "aoColumns": [
				{ "mData": "clientId" },
	            { "mData": "firstName" },
	            { "mData": "lastName" },
	            { "mData": "phone1" },
	            { "mData": "emailAddress" },
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
	            		var action = base_url + 'updateClient/' + full.clientId;
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
	         }]
	    } );
	 
	} );