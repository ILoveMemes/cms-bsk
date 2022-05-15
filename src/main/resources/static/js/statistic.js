function showLast10Statistic() {

    var xValues = [];
    var yValues = [];
    var y2Values = [];
    var barColors = "#FF7600";
    var bar2Colors = "#FFAC68";

    let beginIndex = STATISTIC.length - 10;
    if (beginIndex < 0) {
        beginIndex = 0;
    }

    for (let i = beginIndex; i < STATISTIC.length; i++) {
        let stat = STATISTIC[i];
        xValues.push(new Date(stat.statTime).format('dd.mm.yyyy'));
        yValues.push(stat.allPageLoadsCount);
        y2Values.push(stat.uniqueUsersCount);
    }

    if (chart) {
        chart.destroy();
    }
    chart = new Chart("myChart", {
      type: "bar",
      data: {
          labels: xValues,
          datasets: [{
              label: 'Общее количество посещений',
              backgroundColor: barColors,
              data: yValues
          }, {
              label: 'Уникальные пользователи за сутки',
              backgroundColor: bar2Colors,
              data: y2Values
          }]
      },
      options: {
          responsive: true,
          maintainAspectRatio: false,
          legend: {
              display: true
          },
          scales: {
              yAxes: [{
                  display: true,
                  ticks: {
                      beginAtZero: true
                  }
              }]
          }
      }
    });

}

function showByDateStatistic() {

    var xValues = [];
    var yValues = [];
    var y2Values = [];
    var barColors = "#FF7600";
    var bar2Colors = "#FFAC68";

    for (let i = 0; i < STATISTIC.length; i++) {
        let stat = STATISTIC[i];
        let statDate = new Date(stat.statTime);
        if ((statDate - beginDate >= 0) && (endDate - statDate >= 0)) {
            xValues.push(statDate.format('dd.mm.yyyy'));
            yValues.push(stat.allPageLoadsCount);
            y2Values.push(stat.uniqueUsersCount);
        }
    }

    if (chart) {
        chart.destroy();
    }
    chart = new Chart("myChart", {
      type: "bar",
      data: {
          labels: xValues,
          datasets: [{
              label: 'Общее количество посещений',
              backgroundColor: barColors,
              data: yValues
          }, {
              label: 'Уникальные пользователи за сутки',
              backgroundColor: bar2Colors,
              data: y2Values
          }]
      },
      options: {
          responsive: true,
          maintainAspectRatio: false,
          legend: {
              display: true
          },
          scales: {
              yAxes: [{
                  display: true,
                  ticks: {
                      beginAtZero: true
                  }
              }]
          }
      }
    });

}