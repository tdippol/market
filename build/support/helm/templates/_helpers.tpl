{{/*
Expand the name of the chart.
*/}}
{{- define "mui_app.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}


{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "mui_app.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "mui_app.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "mui_app.labels" -}}
helm.sh/chart: {{ include "mui_app.chart" . }}
{{ include "mui_app.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
app.kubernetes.io/part-of: {{ include "mui_app.name" . }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "mui_app.selectorLabels" -}}
app.kubernetes.io/name: {{ include "mui_app.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "mui_app.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "mui_app.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Populate fully qualified image name
*/}}
{{- define "mui_app.database.image" -}}
{{- printf "%s/%s/%s:%s" .Values.images.registry .Values.images.repo .Values.images.database.name .Values.images.database.tag }}
{{- end -}}

{{- define "mui_app.app.image" -}}
  {{- printf "%s/%s/%s:%s" .Values.images.registry .Values.images.repo .Values.images.app.name .Values.images.app.tag }}
{{- end -}}

{{- define "mui_app.prepare.image" -}}
  {{- printf "%s/%s/%s:%s" .Values.images.registry .Values.images.repo .Values.images.prepare.name .Values.images.prepare.tag }}
{{- end -}}

{{/*
Secret to pull image from gitlab registry
*/}}
{{- define "imagePullSecret" }}
{{- with .Values.imageCredentials }}
{{- printf "{\"auths\":{\"%s\":{\"username\":\"%s\",\"password\":\"%s\",\"email\":\"%s\",\"auth\":\"%s\"}}}" .registry .username .password .email (printf "%s:%s" .username .password | b64enc) | b64enc }}
{{- end }}
{{- end }}
