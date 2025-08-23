document.getElementById("gradeForm").addEventListener("submit", async function(e) {
  e.preventDefault();

  let marksInput = document.getElementById("marks").value;
  let marks = marksInput.split(",").map(m => parseInt(m.trim())).filter(n => !isNaN(n));

  if (marks.length === 0) {
    document.getElementById("result").classList.remove("show");
    document.getElementById("result").innerText = "⚠️ Please enter valid marks!";
    return;
  }

  let response = await fetch("/api/grades/calculate", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ marks })
  });

  if (!response.ok) {
    document.getElementById("result").classList.remove("show");
    document.getElementById("result").innerText = "❌ Error connecting to backend!";
    return;
  }

  let data = await response.json();

  let resultBox = document.getElementById("result");
  resultBox.innerHTML = `
      🎯 Total Marks: <span style="color:#6a82fb">${data.totalMarks}</span> <br>
      📊 Average Percentage: <span style="color:#fc5c7d">${data.averagePercentage.toFixed(2)}%</span> <br>
      🏆 Grade: <strong style="color:green">${data.grade}</strong>
  `;

  resultBox.classList.remove("hidden");
  setTimeout(() => resultBox.classList.add("show"), 50); 
});
