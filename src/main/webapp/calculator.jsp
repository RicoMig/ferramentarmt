<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>RMT Tool - Calculator</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/calculator.css">
    </head>

    <body>

        <nav>
            <a href="index.jsp" class="nav-brand">RMT<span>Tool</span></a>
            <div class="nav-links">
                <a href="index.jsp">Home</a>
                <a href="calculator.jsp" class="active">Calculator</a>
            </div>
        </nav>

        <main>
            <h1>Currency Conversion</h1>

            <div class="calculator-container">

                <div class="calc-header">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                        stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M21 12V7H5a2 2 0 0 1 0-4h14v4"></path>
                        <path d="M3 5v14a2 2 0 0 0 2 2h16v-5"></path>
                        <path d="M18 12a2 2 0 0 0 0 4h4v-4Z"></path>
                    </svg>
                    <h2>Multi-Input Valuation</h2>
                </div>

                <form id="calculator-form" onsubmit="event.preventDefault();">

                    <!-- Master Filter: Game Selection -->
                    <div class="form-group">
                        <label for="game-selector">Select Your Game</label>
                        <div class="input-wrapper game-selector-group">
                            <div class="input-icon">
                                <!-- Game Controller Icon -->
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round">
                                    <rect x="2" y="6" width="20" height="12" rx="2"></rect>
                                    <path d="M6 12h4"></path>
                                    <path d="M8 10v4"></path>
                                    <circle cx="15" cy="13" r="1"></circle>
                                    <circle cx="18" cy="11" r="1"></circle>
                                </svg>
                            </div>
                            <select id="game-selector" class="form-control">
                                <option value="tba">Tibia</option>
                                <option value="tbm">Tibiame</option>
                                <option value="tof">Tales of Fearless</option>
                                <option value="kkl">Kakele</option>
                                <option value="rag">Ragnarok Latam</option>
                                <option value="rat">RagnaTrue</option>
                                <option value="pxg">PokeXGame</option>
                                <option value="pos">Pokestone</option>
                            </select>
                            <div class="select-chevron">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round">
                                    <polyline points="6 9 12 15 18 9"></polyline>
                                </svg>
                            </div>
                        </div>
                    </div>

                    <!-- Group A: In-Game Gold/Silver -->
                    <div class="form-group">
                        <label for="ingame-input">In-Game Gold/Silver</label>
                        <div class="input-wrapper ingame-group">
                            <div class="input-icon">
                                <!-- Coin Pile Icon -->
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round">
                                    <ellipse cx="12" cy="5" rx="9" ry="3"></ellipse>
                                    <path d="M21 12c0 1.66-4 3-9 3s-9-1.34-9-3"></path>
                                    <path d="M3 5v14c0 1.66 4 3 9 3s9-1.34 9-3V5"></path>
                                </svg>
                            </div>
                            <input type="number" id="ingame-input" class="form-control" placeholder="0" min="0"
                                step="any">
                        </div>
                    </div>

                    <!-- Group B: Premium Currency (Cash) -->
                    <div class="form-group">
                        <label for="premium-input">Premium Currency (Cash)</label>
                        <div class="input-wrapper premium-group">
                            <div class="input-icon">
                                <!-- Diamond Icon -->
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round">
                                    <path d="M6 3h12l4 6-10 13L2 9Z"></path>
                                    <path d="M11 3 8 9l4 13 4-13-3-6"></path>
                                    <path d="M2 9h20"></path>
                                </svg>
                            </div>
                            <input type="number" id="premium-input" class="form-control" placeholder="0" min="0"
                                step="any">
                        </div>
                    </div>

                    <!-- Group D: Market Price (Dynamic Override) -->
                    <div class="form-group">
                        <label for="market-price-input">Current Market Price (In-Game cost for 1 Cash)</label>
                        <div class="input-wrapper market-group">
                            <div class="input-icon">
                                <!-- Trending Up Icon -->
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                    <polyline points="23 6 13.5 15.5 8.5 10.5 1 18"></polyline>
                                    <polyline points="17 6 23 6 23 12"></polyline>
                                </svg>
                            </div>
                            <input type="number" id="market-price-input" class="form-control" placeholder="Standard DB Rate" min="0" step="any">
                        </div>
                        <small class="helper-text">Leave empty to use the standard database rate.</small>
                    </div>

                    <!-- Group C: Target Global Currency -->
                    <div class="form-group">
                        <label for="target-currency">Target Currency</label>
                        <div class="input-wrapper currency-group">
                            <div class="input-icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round">
                                    <circle cx="12" cy="12" r="10"></circle>
                                    <line x1="12" y1="8" x2="12" y2="16"></line>
                                    <line x1="8" y1="12" x2="16" y2="12"></line>
                                </svg>
                            </div>
                            <select id="target-currency" class="form-control">
                                <option value="BRL">BRL - Brazilian Real</option>
                                <option value="USD">USD - US Dollar</option>
                                <option value="EUR">EUR - Euro</option>
                            </select>
                        </div>
                    </div>

                    <!-- Action Button -->
                    <button type="submit" class="convert-btn">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                            stroke="currentColor" stroke-width="3" stroke-linecap="round" stroke-linejoin="round">
                            <polyline points="23 4 23 10 17 10"></polyline>
                            <polyline points="1 20 1 14 7 14"></polyline>
                            <path d="M3.51 9a9 9 0 0 1 14.85-3.36L23 10M1 14l4.64 4.36A9 9 0 0 0 20.49 15"></path>
                        </svg>
                        Calculate Value
                    </button>

                    <!-- Total Valuation Card -->
                    <div class="results-section">
                        <div class="results-header">Estimated Value</div>

                        <div class="total-value-container">
                            <sup id="currency-symbol">R$</sup>
                            <span id="total-display">0.00</span>
                        </div>

                        <div class="breakdown-section">
                            <span class="breakdown-label">Premium Equivalent</span>
                            <div class="breakdown-value">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round"
                                    stroke-linejoin="round">
                                    <circle cx="12" cy="12" r="10"></circle>
                                    <path d="M12 8l4 4-4 4"></path>
                                    <path d="M8 12h8"></path>
                                </svg>
                                <span id="paycoin-display">0</span>
                            </div>
                        </div>
                    </div>

                </form>

            </div>
        </main>

        <script>
            document.getElementById('calculator-form').addEventListener('submit', function (e) {
                e.preventDefault();

                const game = document.getElementById('game-selector').value;
                const ingameAmount = parseFloat(document.getElementById('ingame-input').value) || 0;
                const premiumAmount = parseFloat(document.getElementById('premium-input').value) || 0;
                const marketPrice = parseFloat(document.getElementById('market-price-input').value) || 0;
                const targetCurrency = document.getElementById('target-currency').value;

                const data = {
                    game: game,
                    ingameAmount: ingameAmount,
                    premiumAmount: premiumAmount,
                    customMarketPrice: marketPrice,
                    targetCurrency: targetCurrency
                };

                fetch('api/calculate', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                    .then(response => response.json())
                    .then(result => {
                        if (result.success) {
                            // Update UI with calculated values
                            document.getElementById('total-display').innerText = result.finalValue.toFixed(2);
                            document.getElementById('paycoin-display').innerText = result.premiumEquivalent.toFixed(2);

                            // Update Currency Symbol
                            const symbolMap = { 'BRL': 'R$', 'USD': '$', 'EUR': '€' };
                            document.getElementById('currency-symbol').innerText = symbolMap[result.currencyCode] || result.currencyCode;
                        } else {
                            alert('Error calculating: ' + (result.message || 'Unknown error'));
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Error communicating with the back-end.');
                    });
            });
        </script>

    </body>

    </html>