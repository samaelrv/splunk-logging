import requests
from requests.auth import HTTPBasicAuth

# Configuration
SPLUNK_API_URL = "https://localhost:8089/services/search/jobs"
SPLUNK_USERNAME = "ramanath"
SPLUNK_PASSWORD = "@Ramanath123"
DYNATRACE_URL = "https://sno80455.live.dynatrace.com/api/v2/logs/ingest"
DYNATRACE_API_TOKEN = "dt0c01.VQYB2IJJMH4GJCUADW6NQ7IZ.J7L34ICN2THX557BDLNPZ3YGD7Y3JUSVPEUZODW3WMOGMUTTW7NV7LTCF2LM3FXZ"

def fetch_splunk_logs(query):
    auth = HTTPBasicAuth(SPLUNK_USERNAME, SPLUNK_PASSWORD)
    headers = {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
    data = {
        'search': query,
        'output_mode': 'json'
    }
    response = requests.post(SPLUNK_API_URL, headers=headers, data=data, auth=auth, verify=False)
    response.raise_for_status()
    return response.json()

def send_logs_to_dynatrace(log_json):
    headers = {
        'Authorization': f'Api-Token {DYNATRACE_API_TOKEN}',
        'Content-Type': 'application/json'
    }
    response = requests.post(DYNATRACE_URL, headers=headers, json=log_json)
    response.raise_for_status()

def main():
    try:
        # Step 1: Fetch logs from Splunk
        #splunk_query = '"index="order_api_dev" sourcetype="log4j" | table _time, host, source, sourcetype, _raw"'
        splunk_query = "search index=order_api_dev"
        splunk_logs = fetch_splunk_logs(splunk_query)

        # Step 2: Send logs to Dynatrace
        send_logs_to_dynatrace(splunk_logs)

        print("Logs successfully sent to Dynatrace")
    except requests.exceptions.RequestException as e:
        print(f"Error sending logs: {e}")

if __name__ == "__main__":
    main()
