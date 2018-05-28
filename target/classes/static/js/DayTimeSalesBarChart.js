function draw2DBar(salesDataJson, hourArray,chartSubtitle,end_scale) {
	//chartSubtitle="";
	var chart = new iChart.ColumnMulti2D({
		render : 'canvasDiv',
		data : salesDataJson,
		labels : hourArray,
		title : '新世界利莹百货日销售时段图',
		//subtitle : chartSubtitle ,
		subtitle:{
             text:chartSubtitle,
             //color:"#8d9db5",
             fontsize:12,
             font:"微软雅黑",
             textAlign:"left",
             height:50,
             offsetx:60,
             offsety:6
       },
		footnote : '数据来源：电脑科',
		width : document.body.clientWidth * 0.8,
		height : document.body.clientHeight * 0.95,
		background_color : '#ffffff',
		animation:true,
		tip:{
						enable :true
					},
		legend : {
			enable : true,
			background_color : null,
			border : {
				enable : false
			},
            column:1,
			align:"right",
            valign:"top",
            offsetx:-80,
            offsety:-45
		},
		coordinate : {
			background_color : '#f1f1f1',
			scale : [ {
				position : 'left',
				start_scale : 0,
				end_scale : end_scale,
				scale_space : 10
			} ],
			width : '80%',
			height : '70%'
		}
	});
	chart.plugin(new iChart.Custom({
		drawFn : function() {
			// 
			var coo = chart.getCoordinate(), x = coo.get('originx'), y = coo
					.get('originy');
			// 
			chart.target.textAlign('start').textBaseline('bottom').textFont(
					'600 11px Verdana').fillText('金额(万)', x - 40, y - 28,
					false, '#6d869f');

		}
	}));
	chart.draw();
	
}
function draw3DBar(salesDataJson, hourArray,chartSubtitle,end_scale) {
	var chart = new iChart.ColumnMulti3D({
		render : 'canvasDiv',
		data : salesDataJson,
		labels : hourArray,
		title : {
			text : '商场分时段日销售对比',
			color : '#3e576f'
		},
		subtitle:{
            text:chartSubtitle,
            //color:"#8d9db5",
            fontsize:12,
            font:"微软雅黑",
            textAlign:"left",
            height:50,
            offsetx:60,
            offsety:6
      },
		footnote : {
			text : '数据来源：电脑科',
			color : '#909090',
			fontsize : 11,
			padding : '0 44'
		},
		width : document.body.clientWidth * 0.8,
		height : document.body.clientHeight * 0.95,
		background_color : '#ffffff',
		legend : {
			enable : true,
			background_color : null,
			align : 'center',
			valign : 'top',
			row : 1,
			column : 'max',
			border : {
				enable : false
			}
		},
		column_width : 8,// ���ο��
		zScale : 8,// z����ȱ���
		xAngle : 50,
		bottom_scale : 1.1,
		label : {
			color : '#4c4f48'
		},
		sub_option : {

			label : {
				fontweight : 600,
				fontsize : 12,
				color : "#ed7070",
				sign : "square",
				sign_size : 12,
				border : {
					color : "#BCBCBC",
					width : 1
				},
				background_color : "#fefefe"
			}
		},
		tip : {
			enable : true
		},
		animation : true,
		text_space : 16,// 
		coordinate : {
			background_color : '#d7d7d5',
			grid_color : '#a4a4a2',
			color_factor : 0.24,
			board_deep : 10,
			offsety : -10,
			pedestal_height : 10,
			left_board : false,//
			width : '80%',
			height : '70%',
			scale : [ {
				position : 'left',
				start_scale : 0,
				end_scale : end_scale,
				scale_space : 10,
				scale_enable : false,
				label : {
					color : '#4c4f48'
				}
			} ]
		}
	});

	// 
	chart.plugin(new iChart.Custom({
		drawFn : function() {
			// 
			var coo = chart.getCoordinate(), x = coo.get('originx'), y = coo
					.get('originy');
			// 
			chart.target.textAlign('start').textBaseline('bottom').textFont(
					'600 11px Verdana').fillText('金额(万)', x - 40, y - 28,
					false, '#6d869f');

		}
	}));

chart.draw();}