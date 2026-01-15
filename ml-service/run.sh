#!/bin/bash
export TRANSFORMERS_OFFLINE=1
export HF_HUB_OFFLINE=1

uvicorn app.main:app --host 0.0.0.0 --port 8000
