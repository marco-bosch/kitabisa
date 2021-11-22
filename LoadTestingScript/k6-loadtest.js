import http from "k6/http";
import { check } from "k6";
import { Counter } from "k6/metrics";
import { Rate } from "k6/metrics";

let errorRate = new Rate("Error Rate");
let counter4xx = new Counter("Counter 4xx Response");
let counter5xx = new Counter("Counter 5xx Response");

  export const options = {

    vus: 10,
  
    duration: '120s',
  
  };

const SCHEME = "https://";
const BASE_URL = "regres.in";
const PATH = "/api/register";


export default function register() {
  let requestHeaders = {
    "Content-Type": "application/json",
  };

  let requestBody = {
        "email": "eve.holt@reqres.in",
        "password": "pistol"
};

let response = http.request('POST', `${SCHEME}${BASE_URL}${PATH}`, JSON.stringify(requestBody), requestHeaders);

  if (response.status >= 200) {
    console.log("status:", response.status, "body:", response.body);
  }

  check(response, {
    "counters status 200": (r) => r.status === 200,
  }) || errorRate.add(1);

  if (response.status >= 400 && response.status <= 499) {
    counter4xx.add(1);
  }

  if (response.status >= 500 && response.status <= 599) {
    counter5xx.add(1);
  }
}
