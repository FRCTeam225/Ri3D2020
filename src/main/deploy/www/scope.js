var chart = new SmoothieChart({responsive: true});

var app = new Vue({
    el: "#app",
    data: {
        chart: null,
        topics: [],
        plots: [],
        selectedAddTopic: "",
        selectedAddTopicColor: "#00ff00",
        maxValue: 500,
        minValue: 0,
        unitsPerDivision: 100,
        modal: ""
    },

    methods: {
        addPlot: function() {
            if ( this.topics.indexOf(this.selectedAddTopic) == -1 ) 
                return;
            this.plots.push({ topic: this.selectedAddTopic, object: new TimeSeries(), color: this.selectedAddTopicColor });
        },

        removePlot: function(idx) {
            this.plots.splice(idx, 1);
        },

        reinitChart: function() {  
            if ( this.chart != null )
                this.chart.stop();

            var divisions = (this.maxValue-this.minValue)/this.unitsPerDivision;

            this.chart = new SmoothieChart({
                responsive: true,
                horizontalLines:[{color:'#ffffff',lineWidth:1,value:0}],
                maxValue: this.maxValue,
                minValue: this.minValue,
                interpolation:'linear',
                grid: {sharpLines:true, verticalSections: divisions}
            });

            for ( p in this.plots )
              this.chart.addTimeSeries(this.plots[p].object, { strokeStyle: this.plots[p].color, lineWidth: 4 });
 
            this.chart.streamTo(document.getElementById("canvas"), 100);
      },

      recvData: function(data) {
        if ( this.topics.length == 0 ) {
          this.topics = Object.keys(data);
        }

        for ( p in this.plots )
          this.plots[p].object.append(new Date().getTime(), data[this.plots[p].topic]);
      }
    },

    beforeMount: function() {
        this.reinitChart();
    }
});

var sock = new WebSocket("ws://10.2.25.2:5801/state/socket");
sock.onmessage = function(rawmsg) {
  var msg = JSON.parse(rawmsg.data);
  app.recvData(msg);
};

sock.onerror = function() {
  alert("Lost websocket connection");
};

sock.onclose = function() {
  alert("Lost websocket connection");
};
